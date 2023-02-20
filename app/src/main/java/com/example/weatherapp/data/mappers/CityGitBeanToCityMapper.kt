package com.example.weatherapp.data.mappers

import android.util.Log
import com.example.weatherapp.data.beans.CityGitBean
import com.example.weatherapp.domain.CityEntity

class CityGitBeanToCityMapper(cityGitBean: CityGitBean) {

    val citiesBeanItem:(data: CityGitBean) -> CityEntity? = {it.toEntity(cityGitBean)}

    private fun CityGitBean.toEntity(data: CityGitBean) = kotlin.runCatching {
        CityEntity(
            cityName = cityName,
            lat = lat,
            lon = lon,
            isLiked = false
        )
    }.getOrElse {
        Log.e("GitJsonToEntity", "${it.stackTraceToString()}")
        null
    }

}