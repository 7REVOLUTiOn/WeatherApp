package com.example.weatherapp.data.implementations

import com.example.weatherapp.data.beans.WeatherYandexBean
import com.example.weatherapp.data.mappers.WeatherYandexBeanToWeatherEntityMapper
import com.example.weatherapp.domain.CityEntity
import com.example.weatherapp.domain.WeatherEntity
import com.example.weatherapp.domain.interfaces.IGetWeatherFromRemoteRepository
import com.example.weatherapp.utils.TRezult
import com.example.weatherapp.utils.WeatherException
import com.example.weatherapp.utils.logError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetWeatherFromRemoteRepositoryImpl(
    private val getWeatherFromRemoteRepUseCase: suspend (lat:Double, lon:Double) -> WeatherYandexBean
) : IGetWeatherFromRemoteRepository { //TODO(ПОСТАВИТЬ НА DI WetherApi)

    // TODO: через DI

    override suspend fun getWeatherFromYandex(city:CityEntity): TRezult<WeatherEntity> = //TODO(ПЕРЕИМЕНОВАТЬ)
        withContext(Dispatchers.IO) {
            return@withContext runCatching {
                val weatherBeanToEntity =
                    WeatherYandexBeanToWeatherEntityMapper().weatherBeanToEntity
                val beanWeather = getWeatherFromRemoteRepUseCase.invoke(city.lat,city.lon)
                val mapBeanRezult = weatherBeanToEntity(beanWeather)
                if (mapBeanRezult != null && beanWeather != null) { // TODO: удалить
                    TRezult.Success(mapBeanRezult)
                } else {
                    throw Exception("Mapping error")
                }
            }.getOrElse {
                it.logError()
                TRezult.Error(WeatherException.get(it))
            }
        }
}