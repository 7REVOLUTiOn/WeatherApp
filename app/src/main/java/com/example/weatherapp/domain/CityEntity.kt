package com.example.weatherapp.domain

data class CityEntity(
    val cityName: String,
    val lat: Double,
    val lon: Double,
    var isLiked: Boolean
)