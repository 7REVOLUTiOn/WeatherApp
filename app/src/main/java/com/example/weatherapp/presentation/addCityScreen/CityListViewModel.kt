package com.example.weatherapp.presentation.addCityScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.entities.CityEntity
import com.example.weatherapp.utils.LiveDataUtils.asLiveData
import com.example.weatherapp.utils.LiveDataUtils.mValue
import com.example.weatherapp.utils.TRezult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CityListViewModel(
    private val getCitiesListUseCase: suspend () -> TRezult<List<CityEntity>>,
    private val addCityToFavoriteUseCase: suspend (CityEntity) -> Unit,
    private val deleteCityFromFavoriteUseCase: suspend (CityEntity) -> Unit
) : ViewModel() {

    private var job: Job? = null

    private val _listOfCityEntity = MutableLiveData<List<CityEntity>>()
    val listOfCityEntity: LiveData<List<CityEntity>>
        get() = _listOfCityEntity

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading.asLiveData()

    fun addCityToFavorite(cityEntity: CityEntity){
        viewModelScope.launch {
            addCityToFavoriteUseCase.invoke(cityEntity)
        }
    }

    fun deleteCityFromFavorite(cityEntity: CityEntity){
        viewModelScope.launch{
            deleteCityFromFavoriteUseCase.invoke(cityEntity)
        }
    }

    init {
        loadingAllCitiesAsync()
    }

    private fun loadingAllCitiesAsync() {
        job?.cancel()
        job = viewModelScope.launch {
            loadingAllCities()
        }
    }

    //TODO(LIVEDATA ДЛЯ отдельной ошибки, куда будема передавать)
    suspend fun loadingAllCities() = withContext(Dispatchers.Main) {
        // TODO: когда хочешь засетать значеине в LD используй mValue - он более безопасный вариат. но брать значение из ЛД по прежнему классическим value (готово)
        _isLoading.mValue = true
        val reuzlt = getCitiesListUseCase.invoke()
        when (reuzlt) {
            is TRezult.Error -> TODO()  // TODO: ты словишь краш приложения, если вернется TResult.Error
            is TRezult.Success -> _listOfCityEntity.mValue = reuzlt.data
        }
        _isLoading.value = false
    }


}