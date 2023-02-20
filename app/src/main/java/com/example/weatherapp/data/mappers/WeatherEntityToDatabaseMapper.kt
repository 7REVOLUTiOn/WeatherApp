package com.example.weatherapp.data.mappers

import android.util.Log
import com.example.weatherapp.data.dataBase.WeatherData
import com.example.weatherapp.domain.WeatherEntity

class WeatherEntityToDatabaseMapper(weatherEntity: WeatherEntity?) {

    val weatherEntityToData: (weatherEntity: WeatherEntity?) -> WeatherData? = {it?.toEntity()}

    private fun WeatherEntity.toEntity() = kotlin.runCatching {
        WeatherData(
            id = 1,
            temp = temp,
            season = season,
            feelsLike = feelsLike,
            cityName = "Moscow"
        )
    }.getOrElse {
        Log.e("DataToEntityMapper", "${it.stackTraceToString()}")
        null
    }
}