package com.example.weatherapp.utils

sealed class WeatherException(exception: Throwable) : Throwable(exception) {
    class Other(exception: Throwable) : WeatherException(exception)

    override val cause: Throwable = exception //domain

    companion object { //data
        fun get(exception: Throwable): WeatherException {
            exception.logError()
            return when (exception) {
                is WeatherException -> exception
                //дополнять...
                else -> Other(exception)
            } //Чтобы из любой ошибки сделать WeatherException

            //(все примеры через getCities() )
        }
    }

}
