package com.example.weatherapp.utils

sealed class TRezult<T>(
    open val data: T? = null,
    open val exception: WeatherException? = null
) { //Может хранить или данные или ошибки, которые мы превратили в наши ошибки
    data class Success<T>(override val data: T) : TRezult<T>() //Может хранить только данные
    data class Error<T>(override val exception: WeatherException) :
        TRezult<T>() //Может хранить только ошибки
} //Чтобы четко обрабатывать

//Trezult - как бы нужен чтобы получать нулабельный обьект, а отдавать обычный обьект
