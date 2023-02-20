package com.example.weatherapp.data.retrofit

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.beans.CityGitBean
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CitiesAPI {

    @GET("city_coordinates.json")
    suspend fun getCitiesFromGit(): List<CityGitBean>


    companion object {
        private var citiesAPI: CitiesAPI? = null

        fun getInstance(): CitiesAPI {
            if (citiesAPI == null) {
                val okHttpClient = MyOkHttp(
                    isSafe = !BuildConfig.DEBUG
                ).get()
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl("https://7revolution.github.io/WeatherApp/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
                citiesAPI = retrofit.create(CitiesAPI::class.java)
            }
            return citiesAPI!!
        }
    }
}