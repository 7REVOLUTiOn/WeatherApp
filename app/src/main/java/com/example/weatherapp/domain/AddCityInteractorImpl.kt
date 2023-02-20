package com.example.weatherapp.domain

import com.example.weatherapp.domain.interfaces.IAddCityInteractor
import com.example.weatherapp.utils.TRezult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddCityInteractorImpl(
    private val getCitiesFromRemoteRepositoryUseCase: suspend () -> TRezult<List<CityEntity>>,
    private val getWeatherFromRemoteRepositoryUseCase: suspend () -> TRezult<WeatherEntity>,
    private val getDataFromLocalRepositoryUseCase: suspend () -> List<WeatherEntity>,
) : IAddCityInteractor {

    /*val getCities: IGetCitiesFromRemoteRepository = GetCitiesFromRemoteRepositoryImpl()
    val getWeather: IGetWeatherFromRemoteRepository = GetWeatherFromRemoteRepositoryImpl()
    val localRep1: ILocalRepository = LocalRepositoryImpl()*/

    override suspend fun addCityInteractor(): TRezult<List<CityEntity>> =
        withContext(Dispatchers.IO) {
            // TODO: УДАЛИТЬ
            val citiesFromRemoteRep = getCitiesFromRemoteRepositoryUseCase.invoke()
            val citiesFromLocalRep = getDataFromLocalRepositoryUseCase.invoke()
            //val weather = getWeatherFromRemoteRepositoryUseCase.invoke()
            //val cities =
            //val cities = getCitiesFromRemoteRepositoryUseCase.invoke()


            // #1
            /*cities.forEach { cityNtework->
                val isLike = all.find { cityBD->
                    cityNtework.cityName == cityBD.cityName
                } != null
                cityNtework.isLiked = isLike
            }
            return@withContext cities*/


            return@withContext when (citiesFromRemoteRep) {
                is TRezult.Error -> citiesFromRemoteRep
                is TRezult.Success -> {
                    citiesFromRemoteRep.data.forEach { cityNtework ->
                        val isLike = citiesFromLocalRep.find { cityBD ->
                            cityNtework.cityName == cityBD.cityName
                        } != null
                        cityNtework.isLiked = isLike
                    }
                    TRezult.Success(citiesFromRemoteRep.data)
                }
            } //cities.data - потому, что тупо на cities.forEcach - не будет т.к Trezult, а cities.data -
            //уже обращаемся к листу


            /* #2
             val cityNameBD = all.map { it.cityName }
             cities?.forEach {
                 it?.isLiked = cityNameBD.contains(it.cityName)
             }
             return@withContext cities



             if (cities != null){
                 for ((index,i) in (all).withIndex()){
                     if (cities[index]?.cityName == all[index].cityName){
                         cities[index]?.isLiked = true
                     }
                 }
             }*/

            //val sortedByDescending = cities?.sortedByDescending { it?.isLiked }
            //return@withContext sortedByDescending
        }

//  создать класс своих Ошибок + внедрить TResult (данные - Дженерик, ошибка - твой класс Ошибок)
    // оба класса - sealed-классы


}