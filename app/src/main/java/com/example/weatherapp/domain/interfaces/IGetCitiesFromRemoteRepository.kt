package com.example.weatherapp.domain.interfaces

import com.example.weatherapp.domain.entities.CityEntity
import com.example.weatherapp.utils.TRezult

interface IGetCitiesFromRemoteRepository {

    suspend fun getCitiesFromGit(): TRezult<List<CityEntity>>


}