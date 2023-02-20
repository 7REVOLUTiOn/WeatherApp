package com.example.weatherapp.domain

import android.content.Context
import com.example.weatherapp.data.localRep.LocalRepository
import com.example.weatherapp.data.localRep.LocalRepositoryImpl
import com.example.weatherapp.data.remoteRepository.RemoteRepository
import com.example.weatherapp.data.remoteRepository.RemoteRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddCityInteractor(context: Context) {
    val remoteRep: RemoteRepository = RemoteRepositoryImpl()
    val localRep: LocalRepository = LocalRepositoryImpl(context)


    suspend fun invoke() = withContext(Dispatchers.IO){



        val cities = remoteRep.getCitiesFromGit()
        val all = localRep.getAllWeather()
        if (cities != null){
            for ((index,i) in (all).withIndex()){
                if (cities[index]?.cityName == all[index].cityName){
                    cities[index]?.isLiked = true
                }
            }
        }
        val sortedByDescending = cities?.sortedByDescending { it?.isLiked }
        return@withContext sortedByDescending
    }


}