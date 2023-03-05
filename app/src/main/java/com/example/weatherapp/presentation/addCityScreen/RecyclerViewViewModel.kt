package com.example.weatherapp.presentation.addCityScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.AddCityInteractorImpl
import com.example.weatherapp.domain.entities.CityEntity
import com.example.weatherapp.utils.TRezult
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class RecyclerViewViewModel(
    private val getCitiesListUseCase: suspend () -> TRezult<List<CityEntity>>
) : ViewModel() {

    private val _listOfCityEntity = MutableLiveData<List<CityEntity>>()
    val listOfCityEntity: LiveData<List<CityEntity>>
        get() = _listOfCityEntity

    val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        startFragment()
    }

    private fun startFragment() {
        viewModelScope.launch {
            _isLoading.value = true
            val reuzlt = getCitiesListUseCase.invoke()
            _listOfCityEntity.value = reuzlt.data!!
            _isLoading.value = false
        }
    }


}