package com.example.weatherapp.data.mappers

import android.util.Log
import com.example.weatherapp.data.dataBase.WeatherData
import com.example.weatherapp.domain.WeatherEntity

class DataToWeatherEntityMapper(weatherData: WeatherData) {

    val weatherDataToEntity: (data:WeatherData) -> WeatherEntity = {it.toEntity(weatherData)}

    private fun WeatherData.toEntity(data: WeatherData) =
        WeatherEntity(
            id = 1,
            temp = data.temp,
            season = data.season,
            feelsLike = data.feelsLike,
            cityName = data.cityName
        )


}