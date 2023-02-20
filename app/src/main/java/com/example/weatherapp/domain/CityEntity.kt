package com.example.weatherapp.domain

data class CityEntity (
    val cityName: String,
    val lat: String,
    val lon: String,
    var isLiked:Boolean
)