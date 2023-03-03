package com.example.weatherapp.data.dataBase

import androidx.room.*
import com.example.weatherapp.domain.entities.CityEntity
import com.example.weatherapp.domain.entities.WeatherEntity

// TODO: плохое название для таблицы (а мне нравится, просто указал что там хранится)
@Entity(tableName = "CityWeatherData")
data class CityWeatherData(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,


    @Embedded
    val city: CityEntity,

    @Embedded
    val weather: WeatherEntity?



)
