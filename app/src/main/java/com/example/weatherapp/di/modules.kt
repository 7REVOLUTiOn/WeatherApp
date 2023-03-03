package com.example.weatherapp.di

import android.app.Application
import com.example.weatherapp.data.dataBase.Dao
import com.example.weatherapp.data.dataBase.MainDB
import com.example.weatherapp.data.implementations.GetCitiesFromRemoteRepositoryImpl
import com.example.weatherapp.data.implementations.GetWeatherFromRemoteRepositoryImpl
import com.example.weatherapp.domain.interfaces.ILocalRepository
import com.example.weatherapp.data.localRep.LocalRepositoryImpl
import com.example.weatherapp.data.retrofit.CitiesAPI
import com.example.weatherapp.data.retrofit.WeatherAPI
import com.example.weatherapp.domain.AddCityInteractorImpl
import com.example.weatherapp.domain.interfaces.IAddCityInteractor
import com.example.weatherapp.domain.interfaces.IGetCitiesFromRemoteRepository
import com.example.weatherapp.domain.interfaces.IGetWeatherFromRemoteRepository
import com.example.weatherapp.presentation.addCityScreen.CityListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val repositories = module {


    // а РемотРепы знают про API

    factory<ILocalRepository> {
        val dao = get<Dao>()
        LocalRepositoryImpl(
            getAllWeatherUseCase = dao::getAllWeathers,
            addCityToLocalRepositoryUseCase = dao::addCityToLocalRepository,
            deleteCityFromLocalRepositoryUseCase = dao::deleteCityFromLocalRepository
        )
    }

    factory<IGetCitiesFromRemoteRepository> {
        val API = get<CitiesAPI>()
        GetCitiesFromRemoteRepositoryImpl(
            getCitiesFromRemoteRepUseCase = API::getCitiesFromGit
        )
    }

    factory<IGetWeatherFromRemoteRepository> {
        val API = get<WeatherAPI>()
        GetWeatherFromRemoteRepositoryImpl(
            getWeatherFromRemoteRepUseCase = API::getWeatherFromYandex
        ) //TODO (ТОЧНО ТАКЖЕ ДОБАВИТЬ ЮЗКЕЙСЫ)
    }


    factory<IAddCityInteractor> {
        val remoteRepGetCities = get<IGetCitiesFromRemoteRepository>()
        val localRep = get<ILocalRepository>()
        AddCityInteractorImpl(
            getCitiesFromRemoteRepositoryUseCase = remoteRepGetCities::getCitiesFromGit, // TODO: можно записать вот в таком формате
            /**
             * TODO: зачем тебе на DI CityEntity ??? О_о
             * этот параметр приходит из вне.
             * у тебя же тут краш будет, к тому же!
             */
            getDataFromLocalRepositoryUseCase = { localRep.getAllCityWeatherEntityFromDb() },
            addCityToFavoriteUseCase = { localRep.addCityToLocalRepository(it) },
            deleteCityFromFavotiteUseCase = { localRep.deleteCityFromLocalRepository(it) }
        )
    }

    factory<CitiesAPI> { CitiesAPI.getInstance() }
    factory<WeatherAPI> { WeatherAPI.getInstance() }

    single<Dao> {
        MainDB.getDbWeather(application = androidApplication()).getDao()
    }

    viewModel<CityListViewModel> {
        val addCityInteractorImpl = get<IAddCityInteractor>()
        CityListViewModel(
            getCitiesListUseCase = addCityInteractorImpl::getAndSortSitiesFromRemoteAndLocalRep,
            addCityToFavoriteUseCase = addCityInteractorImpl::addLikedCityToLocalRep,
            deleteCityFromFavoriteUseCase = addCityInteractorImpl::deleteCityFromLocalRep
        )
    }
}
