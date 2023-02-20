package com.example.weatherapp.data.remoteRepository

import com.example.weatherapp.domain.CityEntity
import com.example.weatherapp.domain.WeatherEntity

interface RemoteRepository {

    suspend fun getWeatherFromYandex(): WeatherEntity?

    suspend fun getCitiesFromGit(): List<CityEntity?>?

}