package com.example.weatherapp.data.mappers

import com.example.weatherapp.data.dataBase.CityWeatherData
import com.example.weatherapp.domain.entities.CityWeatherEntity

class CityWeatherDataToCityWeatherEntityMapper {


    val cityWeatherDataToCityWeatherEntityMapper: (data: CityWeatherData) -> CityWeatherEntity =
        { it.toEntity() }

    private fun CityWeatherData.toEntity() =
        CityWeatherEntity(
            city = city,
            weather = weather,
            isLike = true
            /**
             * TODO: почему то, что возвращается из БД помечано как "нелюбимое"? (готово)
             * все что возвращается из БД - 100% любимое.
             * в модели для БД этого isLike может даже не быть. в маппере и БД в Ентити можно это добавлять.
             */
        )

}