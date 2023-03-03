package com.example.weatherapp.data.mappers

import com.example.weatherapp.data.beans.CityGitBean
import com.example.weatherapp.domain.entities.CityEntity
import com.example.weatherapp.utils.logError

class CityBeanToCityEntityMapper {


    val citiesBeanItem: (data: CityGitBean) -> CityEntity? = { it.toEntity() }

    private fun CityGitBean.toEntity() = runCatching {
        CityEntity(
            cityName = cityName,
            lat = lat.toDouble(),
            lon = lon.toDouble(),
            isLiked = false
        )
    }.getOrElse {

        it.logError()
        null
    }

}