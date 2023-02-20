package com.example.weatherapp.data.localRep

import com.example.weatherapp.domain.WeatherEntity

interface ILocalRepository {

    suspend fun getAllWeather(): List<WeatherEntity>

}