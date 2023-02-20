package com.example.weatherapp.data.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CityWeather")
data class WeatherData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    /*@ColumnInfo(name = "city")
    var city:String,*/

    @ColumnInfo(name = "temp")
    val temp: String,

    @ColumnInfo(name = "season")
    val season: String,

    @ColumnInfo(name = "feelsLike")
    val feelsLike: String,

    @ColumnInfo(name = "cityName")
    val cityName: String
)
