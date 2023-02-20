package com.example.weatherapp.data.mappers

import android.util.Log
import com.example.weatherapp.data.beans.WeatherYandexBean
import com.example.weatherapp.domain.WeatherEntity

class WeatherYandexBeanToWeatherEntityMapper {

    val weatherBeanToEntity: (bean:WeatherYandexBean) -> WeatherEntity? = {it.toEntity()}

    private fun WeatherYandexBean.toEntity() = kotlin.runCatching {
        Log.d("Test321","Запуск мапперва ")
        WeatherEntity(
            id = 0,
            temp = fact!!.temp,
            season = fact.season,
            feelsLike = fact.feelsLike,
            cityName = "Moscow"
        )
    }.getOrElse {
        Log.e("WeatherMapper", "${it.stackTraceToString()}")
        null
    }

}