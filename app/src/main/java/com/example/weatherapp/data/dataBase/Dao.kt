package com.example.weatherapp.data.dataBase

import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {

    //Weather DataBase
    @Query("SELECT * FROM CityWeather")
    fun getAllWeathers():List<WeatherData>

    @Query("SELECT * FROM CityWeather WHERE cityName = :cityName")
    fun getByNameWeather(cityName:String):WeatherData

    @Insert
    fun insertWeather(weather: WeatherData?)

    @Update
    fun updateWeathers (weather: WeatherData?)

    @Delete
    fun deleteWeather(weather: WeatherData?)

    //TODO("МБ сделать два разных интерфйеса дао для каждой таблицы (с погодой и с городами)





}