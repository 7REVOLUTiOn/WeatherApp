package com.example.weatherapp.presentation.addCityScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.entities.CityEntity
import com.example.weatherapp.utils.LiveDataUtils.mValue
import com.example.weatherapp.utils.TRezult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecyclerViewViewModel(
    private val getCitiesListUseCase: suspend () -> TRezult<List<CityEntity>>
) : ViewModel() {

    private var job: Job? = null

    private val _listOfCityEntity = MutableLiveData<List<CityEntity>>()
    val listOfCityEntity: LiveData<List<CityEntity>>
        get() = _listOfCityEntity

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        loadingAllCitiesAsync()
        Log.d("Test321","Запуск ViewModel")
    }

    private fun loadingAllCitiesAsync() {
        job?.cancel()
        job = viewModelScope.launch {
            loadingAllCities()
        }
    }

    suspend fun loadingAllCities() = withContext(Dispatchers.Main){
        Log.d("Test321","Загружаем rezult")
        _isLoading.value = true
        val reuzlt = getCitiesListUseCase.invoke()
        when (reuzlt) {
            is TRezult.Error -> TODO()
            is TRezult.Success -> _listOfCityEntity.mValue = reuzlt.data
        }
        Log.d("Testing","Rezult: ${reuzlt.data}")
        _isLoading.value = false
    }


}