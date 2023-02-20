package com.example.weatherapp.data.localRep

import com.example.weatherapp.data.dataBase.WeatherData
import com.example.weatherapp.domain.WeatherEntity

interface LocalRepository {

    suspend fun getDataByCityName(cityName:String): WeatherEntity
    suspend fun saveData(weatherEntity: WeatherEntity)
    suspend fun getAllWeather():List<WeatherEntity>

}