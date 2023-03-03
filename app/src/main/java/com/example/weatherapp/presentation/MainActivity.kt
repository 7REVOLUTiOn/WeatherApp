package com.example.weatherapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.R
import com.example.weatherapp.domain.interfaces.ILocalRepository
import com.example.weatherapp.domain.interfaces.IGetCitiesFromRemoteRepository
import com.example.weatherapp.domain.interfaces.IGetWeatherFromRemoteRepository
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val getCities: IGetCitiesFromRemoteRepository by inject()
        val getWeather: IGetWeatherFromRemoteRepository by inject()
        val localRep: ILocalRepository by inject()


        //MainScope().launch {

            //val cities = getCities.getCitiesFromGit()
            //val weather = getWeather.getWeatherFromYandex()


            //val weather = weatherInteractor.invoke()

            //val weather = remoteRep.getWeatherFromYandex()
            //Log.d("Testing", "weather: $weather, cities: $cities")


            //val weather = weatherInteractor.invoke()
            // val cites = remoteRep.getCitiesFromGit()
            //val db = localRep.getAllWeather()
            //Log.d("MainTest","Cities: $cites, Weather: $weather, db: $")
            /*kotlin.runCatching {
                10/0
            }.getOrElse {
                it.logError()
                logInfo("")
            }*/

        //}
    }
}