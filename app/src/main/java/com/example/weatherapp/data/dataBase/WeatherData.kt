package com.example.weatherapp.data.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CityWeather")
data class WeatherData(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,

    /*@ColumnInfo(name = "city")
    var city:String,*/

    @ColumnInfo(name = "temp")
    var temp:String,

    @ColumnInfo(name = "season")
    var season:String,

    @ColumnInfo(name = "feelsLike")
    val feelsLike:String,

    @ColumnInfo(name = "cityName")
    var cityName:String
)