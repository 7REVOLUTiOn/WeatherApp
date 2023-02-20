package com.example.weatherapp.data.localRep

import android.app.Application
import com.example.weatherapp.data.dataBase.Dao
import com.example.weatherapp.data.dataBase.MainDB
import com.example.weatherapp.data.dataBase.WeatherData
import com.example.weatherapp.data.mappers.DataToWeatherEntityMapper
import com.example.weatherapp.domain.WeatherEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalRepositoryImpl(
    private val getAllWeatherUseCase: suspend () -> List<WeatherData>
) : ILocalRepository {




    override suspend fun getAllWeather(): List<WeatherEntity> =
        withContext(Dispatchers.IO) {
            val dataToWeatherEntity =
                DataToWeatherEntityMapper().weatherDataToEntity //маппер как отдельная переменная класса
            val res = getAllWeatherUseCase.invoke()
            val listOfEntity = res.map {
                dataToWeatherEntity(it)
            }

            return@withContext listOfEntity
        }
}