package com.example.weatherapp.data.remoteRepository

import android.util.Log
import com.example.weatherapp.data.mappers.CityGitBeanToCityMapper
import com.example.weatherapp.data.mappers.WeatherYandexBeanToWeatherEntityMapper
import com.example.weatherapp.data.retrofit.WeatherAPI
import com.example.weatherapp.domain.CityEntity
import com.example.weatherapp.domain.WeatherEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class RemoteRepositoryImpl(private val api: WeatherAPI = WeatherAPI.getInstance()) :
    RemoteRepository {


    override suspend fun getWeatherFromYandex(): WeatherEntity? =
        withContext(Dispatchers.IO) {
            return@withContext kotlin.runCatching {
                Log.d("Test321", "Not mapped weather: что за хуйня")
                val beanWeather = api.getWeatherFromYandex("55.75396", "37.620393")
                Log.d("Test321", "Not mapped weather: $beanWeather")
                val mapBeanRezult = WeatherYandexBeanToWeatherEntityMapper().weatherBeanToEntity(beanWeather)
                if (mapBeanRezult != null && beanWeather != null) {
                    mapBeanRezult
                } else {
                    throw Exception("Mapping error")
                }
            }.getOrElse {
                Log.d("kokoko", "${it.stackTraceToString()}")
                null
            }
        }

    override suspend fun getCitiesFromGit(): List<CityEntity?>? =
        withContext(Dispatchers.IO) {
            return@withContext kotlin.runCatching {
                val listOfBeansCity = api.getCitiesFromGit()
                var listOfEntity: MutableList<CityEntity?> = mutableListOf()
                for (i in listOfBeansCity) {
                    listOfEntity.add(CityGitBeanToCityMapper(i).citiesBeanItem(i))
                }
                listOfEntity
            }.getOrElse {
                null
            }
        }
}