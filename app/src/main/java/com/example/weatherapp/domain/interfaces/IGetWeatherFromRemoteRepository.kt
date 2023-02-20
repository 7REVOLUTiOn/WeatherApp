package com.example.weatherapp.domain.interfaces

import com.example.weatherapp.domain.CityEntity
import com.example.weatherapp.domain.WeatherEntity
import com.example.weatherapp.utils.TRezult

interface IGetWeatherFromRemoteRepository {

    suspend fun getWeatherFromYandex(city: CityEntity): TRezult<WeatherEntity>

}