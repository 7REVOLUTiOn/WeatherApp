package com.example.weatherapp.domain

data class WeatherEntity(
    val id:Int,
    var temp:String,
    var season:String,
    var feelsLike:String,
    val cityName:String
)