package com.example.weatherapp.domain

import com.example.weatherapp.domain.entities.CityEntity
import com.example.weatherapp.domain.entities.CityWeatherEntity
import com.example.weatherapp.domain.interfaces.IAddCityInteractor
import com.example.weatherapp.utils.TRezult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddCityInteractorImpl(
    private val getCitiesFromRemoteRepositoryUseCase: suspend () -> TRezult<List<CityEntity>>,
    private val getDataFromLocalRepositoryUseCase: suspend () -> List<CityWeatherEntity>,
    private val addCityToFavoriteUseCase: suspend (CityEntity) -> Unit,
    private val deleteCityFromFavotiteUseCase: suspend (CityEntity) -> Unit
) : IAddCityInteractor {


    override suspend fun addLikedCityToLocalRep(cityEntity: CityEntity) =
        withContext(Dispatchers.IO){
        addCityToFavoriteUseCase.invoke(cityEntity)
    }

    override suspend fun deleteCityFromLocalRep(cityEntity: CityEntity) =
        withContext(Dispatchers.IO){
        deleteCityFromFavotiteUseCase.invoke(cityEntity)
    }

    override suspend fun getAndSortSitiesFromRemoteAndLocalRep(): TRezult<List<CityEntity>> =
        withContext(Dispatchers.IO) {
            val citiesFromRemoteRep = getCitiesFromRemoteRepositoryUseCase.invoke()
            val cityWeatherEntityFromLocalRep = getDataFromLocalRepositoryUseCase.invoke()

            return@withContext when (citiesFromRemoteRep) {
                is TRezult.Error -> citiesFromRemoteRep
                is TRezult.Success -> {
                    citiesFromRemoteRep.data.forEach { cityNtework ->
                        val isLike = cityWeatherEntityFromLocalRep.find { cityBD ->
                            cityNtework.cityName == cityBD.city.cityName
                        } != null
                        cityNtework.isLiked = isLike
                    }


                    TRezult.Success(citiesFromRemoteRep.data )
                }
            } //cities.data - потому, что тупо на cities.forEcach - не будет т.к Trezult, а cities.data -
            //уже обращаемся к листу
        }

//  создать класс своих Ошибок + внедрить TResult (данные - Дженерик, ошибка - твой класс Ошибок)
    // оба класса - sealed-классы


}