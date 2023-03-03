package com.example.weatherapp.data.dataBase

import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {

    //Weather DataBase
    @Query("SELECT * FROM CityWeatherData")
    fun getAllWeathers(): List<CityWeatherData>

    @Insert
    fun addCityToLocalRepository(cityWeatherData: CityWeatherData)

    @Query ("DELETE FROM CityWeatherData WHERE cityName = :cityName")
    fun deleteCityFromLocalRepository(cityName:String)


}