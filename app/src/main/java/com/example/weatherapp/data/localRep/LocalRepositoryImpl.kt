package com.example.weatherapp.data.localRep

import com.example.weatherapp.data.dataBase.CityWeatherData
import com.example.weatherapp.data.mappers.CityWeatherDataToCityWeatherEntityMapper
import com.example.weatherapp.domain.entities.CityWeatherEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalRepositoryImpl(
    private val getAllWeatherUseCase: suspend () -> List<CityWeatherData>
) : ILocalRepository {




    override suspend fun getAllCityWeatherEntityFromDb(): List<CityWeatherEntity> =
        withContext(Dispatchers.IO) {
            val dataToWeatherEntity =
                CityWeatherDataToCityWeatherEntityMapper().cityWeatherDataToCityWeatherEntityMapper //маппер как отдельная переменная класса
            val res = getAllWeatherUseCase.invoke()
            val listOfEntity = res.map {
                dataToWeatherEntity(it)
            }

            return@withContext listOfEntity
        }
}