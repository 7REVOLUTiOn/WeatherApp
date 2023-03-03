package com.example.weatherapp.data.mappers

import com.example.weatherapp.data.dataBase.CityWeatherData
import com.example.weatherapp.domain.entities.CityWeatherEntity

class CityWeatherDataToCityWeatherEntityMapper {


    val cityWeatherDataToCityWeatherEntityMapper: (data: CityWeatherData) -> CityWeatherEntity = { it.toEntity() }

    private fun CityWeatherData.toEntity() =
        CityWeatherEntity(
            city = city,
            weather = weather,
            isLike = false
        )

}