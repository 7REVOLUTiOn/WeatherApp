package com.example.weatherapp.data.localRep

import com.example.weatherapp.domain.entities.CityEntity
import com.example.weatherapp.domain.entities.CityWeatherEntity
import com.example.weatherapp.domain.entities.WeatherEntity

// TODO: интрфейс репозитория - домейн слой
interface ILocalRepository {

    suspend fun getAllCityWeatherEntityFromDb(): List<CityWeatherEntity>

    suspend fun addCityToLocalRepository(cityEntity: CityEntity)

    suspend fun deleteCityFromLocalRepository(cityEntity: CityEntity)

}