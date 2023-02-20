package com.example.weatherapp.domain.interfaces

import com.example.weatherapp.domain.CityEntity
import com.example.weatherapp.utils.TRezult

interface IAddCityInteractor {

    suspend fun addCityInteractor(): TRezult<List<CityEntity>>


}