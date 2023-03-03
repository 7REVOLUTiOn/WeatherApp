package com.example.weatherapp.utils

import android.content.Context
import com.example.weatherapp.R

object UiExceptionText {
    fun returnStringByException(weatherException: WeatherException, context: Context): String {
        return when (weatherException) {
            is WeatherException.NoInternet -> context.getString(R.string.No_Internet)
            is WeatherException.Other -> context.getString(R.string.weather_exception_other)
        }
    }
}
