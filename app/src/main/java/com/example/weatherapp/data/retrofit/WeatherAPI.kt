package com.example.weatherapp.data.retrofit

import android.util.Log
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.beans.CityGitBean
import com.example.weatherapp.data.beans.WeatherYandexBean
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherAPI {


    @GET("forecast") //Имя метода на сервере (указываем в аннотации)
    @Headers("X-Yandex-API-Key: 7b11fcb7-4aa2-4e66-9d2d-b1db8c77805a")
    suspend fun getWeatherFromYandex(
        @Query("lat") lat: String,
        @Query("lon") lon: String
    ): WeatherYandexBean

    @GET("https://7revolution.github.io/WeatherApp/city_coordinates.json")
    suspend fun getCitiesFromGit():List<CityGitBean>


    companion object {
        private var weatherAPI:WeatherAPI? = null

        fun getInstance(): WeatherAPI {
            if (weatherAPI == null) {
                val okHttpClient = MyOkHttp(
                    isSafe = !BuildConfig.DEBUG
                ).get()
                //Log.d("Test321", "comp")
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl("https://api.weather.yandex.ru/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
                weatherAPI = retrofit.create(WeatherAPI::class.java)
                //Log.d("Test321", "Возращаем бин")

                //return retrofit.create(WeatherAPI::class.java)
            }
            return weatherAPI!!
        }
    }
}