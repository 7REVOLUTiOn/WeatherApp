package com.example.weatherapp.data.dataBase

import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {

    //Weather DataBase
    @Query("SELECT * FROM CityWeather")
    fun getAllWeathers(): List<WeatherData>


}