package com.example.weatherapp.domain.entities

data class CityWeatherEntity(
    val city: CityEntity,
    val weather: WeatherEntity?,
    var isLike: Boolean
)
