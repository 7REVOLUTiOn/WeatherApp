package com.example.weatherapp.data.mappers

import android.util.Log
import com.example.weatherapp.data.beans.WeatherYandexBean
import com.example.weatherapp.domain.WeatherEntity
import com.example.weatherapp.utils.logError

class WeatherYandexBeanToWeatherEntityMapper {

    val weatherBeanToEntity: (bean: WeatherYandexBean) -> WeatherEntity? = { it.toEntity() }

    private fun WeatherYandexBean.toEntity() = runCatching {
        Log.d("Test321", "Запуск мапперва ")
        WeatherEntity(
            //id = 0,
            temp = fact!!.temp,
            season = fact.season,
            feelsLike = fact.feelsLike,
            cityName = "Moscow"
        )
    }.getOrElse {
        it.logError()
        null
    }

}