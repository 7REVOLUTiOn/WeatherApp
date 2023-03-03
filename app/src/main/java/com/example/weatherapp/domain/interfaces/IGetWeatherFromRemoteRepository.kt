package com.example.weatherapp.domain.interfaces

import com.example.weatherapp.domain.entities.CityEntity
import com.example.weatherapp.domain.entities.WeatherEntity
import com.example.weatherapp.utils.TRezult

interface IGetWeatherFromRemoteRepository {

    suspend fun getWeatherFromRemoteRep(city: CityEntity): TRezult<WeatherEntity>

}