package com.example.weatherapp.data.mappers


import com.example.weatherapp.data.dataBase.CityWeatherData
import com.example.weatherapp.domain.entities.CityEntity
import com.example.weatherapp.domain.entities.CityWeatherEntity
import com.example.weatherapp.domain.entities.WeatherEntity

class CityEntityAndWeatherEntityToCityWeatherDataMapper {

    val weatherEntityToData: (cityEntity: CityEntity, weatherEntity: WeatherEntity?) -> CityWeatherData =
        { cityEntity, weatherEntity ->
            CityWeatherData(id = 0, cityEntity, weatherEntity)
        }


}