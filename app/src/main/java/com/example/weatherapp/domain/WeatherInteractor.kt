package com.example.weatherapp.domain

import android.content.Context
import com.example.weatherapp.data.localRep.LocalRepository
import com.example.weatherapp.data.localRep.LocalRepositoryImpl
import com.example.weatherapp.data.remoteRepository.RemoteRepository
import com.example.weatherapp.data.remoteRepository.RemoteRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherInteractor(context: Context) {

    val remoteRep: RemoteRepository = RemoteRepositoryImpl()
    val localRep: LocalRepository = LocalRepositoryImpl(context)

    suspend fun invoke() = withContext(Dispatchers.IO){
        val weather = remoteRep.getWeatherFromYandex()
        if (weather != null){
            localRep.saveData(weather)
        }

        return@withContext weather
    }

}