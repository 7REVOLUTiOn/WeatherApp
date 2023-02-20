package com.example.weatherapp.data.mappers


import com.example.weatherapp.data.dataBase.WeatherData
import com.example.weatherapp.domain.WeatherEntity

class WeatherEntityToDatabaseMapper {

    val weatherEntityToData: (weatherEntity: WeatherEntity) -> WeatherData = { it.toEntity() }

    private fun WeatherEntity.toEntity() =
        WeatherData(
            //id = 1,
            temp = temp,
            season = season,
            feelsLike = feelsLike,
            cityName = "Moscow"
        )

}