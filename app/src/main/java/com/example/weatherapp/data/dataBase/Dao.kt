package com.example.weatherapp.data.dataBase

import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {

    //Weather DataBase
    @Query("SELECT * FROM CityWeatherData")
    fun getAllWeathers(): List<CityWeatherData>


}