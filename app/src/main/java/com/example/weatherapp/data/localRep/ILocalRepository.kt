package com.example.weatherapp.data.localRep

import com.example.weatherapp.domain.entities.CityWeatherEntity
import com.example.weatherapp.domain.entities.WeatherEntity

interface ILocalRepository {

    suspend fun getAllCityWeatherEntityFromDb(): List<CityWeatherEntity>

}