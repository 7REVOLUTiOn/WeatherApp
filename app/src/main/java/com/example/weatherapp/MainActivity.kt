package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.weatherapp.data.localRep.LocalRepository
import com.example.weatherapp.data.localRep.LocalRepositoryImpl
import com.example.weatherapp.data.remoteRepository.RemoteRepository
import com.example.weatherapp.data.remoteRepository.RemoteRepositoryImpl
import com.example.weatherapp.domain.AddCityInteractor
import com.example.weatherapp.domain.WeatherInteractor
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val remoteRep: RemoteRepository = RemoteRepositoryImpl()
        val localRep: LocalRepository = LocalRepositoryImpl(this)

        val weatherInteractor = WeatherInteractor(this)
        val addCityInteractor = AddCityInteractor(this)


        MainScope().launch {

            val weather = weatherInteractor.invoke()

            


            //val weather = weatherInteractor.invoke()
           // val cites = remoteRep.getCitiesFromGit()
            //val db = localRep.getAllWeather()
            //Log.d("MainTest","Cities: $cites, Weather: $weather, db: $")




        }

    }
}