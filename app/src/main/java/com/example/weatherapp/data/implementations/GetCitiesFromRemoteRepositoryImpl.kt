package com.example.weatherapp.data.implementations

import com.example.weatherapp.data.beans.CityGitBean
import com.example.weatherapp.data.mappers.CityBeanToCityEntityMapper
import com.example.weatherapp.domain.entities.CityEntity
import com.example.weatherapp.domain.interfaces.IGetCitiesFromRemoteRepository
import com.example.weatherapp.utils.TRezult
import com.example.weatherapp.utils.WeatherException
import com.example.weatherapp.utils.logError
import com.example.weatherapp.utils.logInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCitiesFromRemoteRepositoryImpl(
    private val getCitiesFromRemoteRepUseCase: suspend () -> List<CityGitBean>
) : IGetCitiesFromRemoteRepository {



    override suspend fun getCitiesFromGit(): TRezult<List<CityEntity>> =
        withContext(Dispatchers.IO) {
            return@withContext runCatching {
                val listOfBeansCity = getCitiesFromRemoteRepUseCase.invoke()
                val citybeanToEntity = CityBeanToCityEntityMapper().citiesBeanItem //вспомогательный
                val listOfEntity = listOfBeansCity.mapNotNull {
                    citybeanToEntity(it)
                }
                logInfo("Битые данные при получеление бинов с городами ${listOfBeansCity - listOfEntity}")

                TRezult.Success(listOfEntity) //Т.к все успешно, то в success, который хранит только даныне
            }.getOrElse {

                it.logError()
                TRezult.Error(WeatherException.get(it)) //error - т.к ошибка, а дальше берем ошибку и превращаем в нашу ошибку
            }
        }
}