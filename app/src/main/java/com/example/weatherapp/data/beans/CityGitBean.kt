package com.example.weatherapp.data.beans

import com.google.gson.annotations.SerializedName

data class CityGitBean(
    @SerializedName("cityName") val cityName: String,
    @SerializedName("lat") val lat: String,
    @SerializedName("lon") val lon: String
)

