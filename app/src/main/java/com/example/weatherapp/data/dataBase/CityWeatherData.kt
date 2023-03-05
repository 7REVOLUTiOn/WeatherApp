package com.example.weatherapp.data.dataBase

import androidx.room.*
import com.example.weatherapp.domain.entities.CityEntity
import com.example.weatherapp.domain.entities.WeatherEntity

@Entity(tableName = "CityWeatherData")
data class CityWeatherData(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @Embedded
    val city: CityEntity,

    @Embedded
    val weather: WeatherEntity? // TODO: разве он не может быть null ? (готово) (Еще раз почему он может быть null?)



)
