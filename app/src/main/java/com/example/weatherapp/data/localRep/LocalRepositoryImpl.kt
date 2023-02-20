package com.example.weatherapp.data.localRep

import android.content.Context
import com.example.weatherapp.data.dataBase.Dao
import com.example.weatherapp.data.dataBase.MainDb
import com.example.weatherapp.data.dataBase.WeatherData
import com.example.weatherapp.data.mappers.DataToWeatherEntityMapper
import com.example.weatherapp.data.mappers.WeatherEntityToDatabaseMapper
import com.example.weatherapp.domain.CityEntity
import com.example.weatherapp.domain.WeatherEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalRepositoryImpl(context: Context):LocalRepository {

    val mainDb = MainDb.getDbWeather(context)
    val dao:Dao = mainDb.getDao()

    override suspend fun getDataByCityName(cityName: String): WeatherEntity {
        val res = dao.getByNameWeather(cityName)
        val mapRes = DataToWeatherEntityMapper(res).weatherDataToEntity(res)
        return mapRes
    }

    override suspend fun saveData(weatherEntity: WeatherEntity)  = withContext(Dispatchers.IO) {
        val res = WeatherEntityToDatabaseMapper(weatherEntity).weatherEntityToData(weatherEntity)
        dao.updateWeathers(res)
    }

    override suspend fun getAllWeather(): List<WeatherEntity> {
        val res = dao.getAllWeathers()
        var listOfEntity: MutableList<WeatherEntity> = mutableListOf()
        for (i in res){
            listOfEntity.add(DataToWeatherEntityMapper(i).weatherDataToEntity(i))
        }
        return listOfEntity
    }

}