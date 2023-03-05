package com.example.weatherapp.di

import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.weatherapp.data.dataBase.Dao
import com.example.weatherapp.data.dataBase.MainDB
import com.example.weatherapp.data.implementations.GetCitiesFromRemoteRepositoryImpl
import com.example.weatherapp.data.implementations.GetWeatherFromRemoteRepositoryImpl
import com.example.weatherapp.data.localRep.ILocalRepository
import com.example.weatherapp.data.localRep.LocalRepositoryImpl
import com.example.weatherapp.data.retrofit.CitiesAPI
import com.example.weatherapp.data.retrofit.WeatherAPI
import com.example.weatherapp.domain.AddCityInteractorImpl
import com.example.weatherapp.domain.interfaces.IAddCityInteractor
import com.example.weatherapp.domain.interfaces.IGetCitiesFromRemoteRepository
import com.example.weatherapp.domain.interfaces.IGetWeatherFromRemoteRepository
import com.example.weatherapp.presentation.addCityScreen.RecyclerViewViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val repositories = module {


    // а РемотРепы знают про API

    factory<ILocalRepository> {
        val dao = get<Dao>()
        LocalRepositoryImpl(
            getAllWeatherUseCase = dao::getAllWeathers
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
        val remoteRepGetWeather = get<IGetWeatherFromRemoteRepository>()
        val localRep = get<ILocalRepository>()

        AddCityInteractorImpl(
            getCitiesFromRemoteRepositoryUseCase = remoteRepGetCities::getCitiesFromGit, // TODO: можно записать вот в таком формате
                /**
                 * TODO: зачем тебе на DI CityEntity ??? О_о
                 * этот параметр приходит из вне.
                 * у тебя же тут краш будет, к тому же!
                 */
            getDataFromLocalRepositoryUseCase = { localRep.getAllCityWeatherEntityFromDb() }
        )
    }


}


val retrofit = module {
    factory<CitiesAPI> { CitiesAPI.getInstance() }
    factory<WeatherAPI> { WeatherAPI.getInstance() }
}

val database = module {
    single<Dao> {
        MainDB.getDbWeather(application = androidApplication()).getDao()
    }
}

val viewModel = module {

}
