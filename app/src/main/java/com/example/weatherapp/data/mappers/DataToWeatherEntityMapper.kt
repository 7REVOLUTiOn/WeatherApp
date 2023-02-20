package com.example.weatherapp.data.mappers

import com.example.weatherapp.data.dataBase.WeatherData
import com.example.weatherapp.domain.WeatherEntity

class DataToWeatherEntityMapper {


    val weatherDataToEntity: (data: WeatherData) -> WeatherEntity = { it.toEntity() }

    private fun WeatherData.toEntity() =
        WeatherEntity(
            //id = id,
            temp = temp,
            season = season,
            feelsLike = feelsLike,
            cityName = cityName
        )


}