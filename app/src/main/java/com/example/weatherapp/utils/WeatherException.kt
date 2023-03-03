package com.example.weatherapp.utils

import java.net.ConnectException
import java.net.UnknownHostException

sealed class WeatherException(exception: Throwable) : Throwable(exception) {
    class Other(exception: Throwable) : WeatherException(exception)

    class NoInternet(exception: Throwable) : WeatherException(exception)

    override val cause: Throwable = exception //domain

    companion object { //data
        fun get(exception: Throwable): WeatherException {
            exception.logError()
            return when (exception) {
                is WeatherException -> exception
                is UnknownHostException -> NoInternet(exception)
                is ConnectException -> NoInternet(exception)
                //Дополнять
                else -> Other(exception)
            } //Чтобы из любой ошибки сделать WeatherException

            //(все примеры через getCities() )
        }
    }

}
