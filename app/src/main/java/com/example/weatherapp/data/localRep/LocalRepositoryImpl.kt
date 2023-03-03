package com.example.weatherapp.data.localRep

import com.example.weatherapp.data.dataBase.CityWeatherData
import com.example.weatherapp.data.mappers.CityEntityAndWeatherEntityToCityWeatherDataMapper
import com.example.weatherapp.data.mappers.CityWeatherDataToCityWeatherEntityMapper
import com.example.weatherapp.domain.entities.CityEntity
import com.example.weatherapp.domain.entities.CityWeatherEntity
import com.example.weatherapp.domain.interfaces.ILocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalRepositoryImpl(
    private val getAllWeatherUseCase: suspend () -> List<CityWeatherData>,
    private val addCityToLocalRepositoryUseCase: suspend (CityWeatherData) -> Unit,
    private val deleteCityFromLocalRepositoryUseCase: suspend (String) -> Unit
) : ILocalRepository {

    val cityEntityToCityWeatherData =
        CityEntityAndWeatherEntityToCityWeatherDataMapper().weatherEntityToData

    val dataToWeatherEntity =
        CityWeatherDataToCityWeatherEntityMapper().cityWeatherDataToCityWeatherEntityMapper //маппер как отдельная переменная класса

    override suspend fun addCityToLocalRepository(cityEntity: CityEntity) {
        val mappedCityWeatherEntity = cityEntityToCityWeatherData(cityEntity, null)
        addCityToLocalRepositoryUseCase.invoke(mappedCityWeatherEntity)
    }

    override suspend fun deleteCityFromLocalRepository(cityEntity: CityEntity) {
        deleteCityFromLocalRepositoryUseCase.invoke(cityEntity.cityName)
    }

    override suspend fun getAllCityWeatherEntityFromDb(): List<CityWeatherEntity> =
        withContext(Dispatchers.IO) {
            val res = getAllWeatherUseCase.invoke()
            val listOfEntity = res.map {
                dataToWeatherEntity(it)
            }
            return@withContext listOfEntity
        }
}