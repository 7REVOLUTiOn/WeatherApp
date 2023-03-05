package com.example.weatherapp.presentation.addCityScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.core.view.forEach
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentAddCityRecyclerViewBinding
import com.example.weatherapp.di.viewModelModule
import com.example.weatherapp.domain.entities.CityEntity
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecyclerViewFragment : Fragment(R.layout.fragment_add_city_recycler_view) {

    private val binding by viewBinding(FragmentAddCityRecyclerViewBinding::bind)

    private val viewModel by viewModel<RecyclerViewViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Test321","Запуск RV")
        observeViewModel(view)
    }

    private fun observeViewModel(view: View) {
        val progressBar = binding.progressBar
        viewModel.isLoading.observe(viewLifecycleOwner){
            when(it){
                true -> progressBar.isVisible = false
                false -> progressBar.isVisible = false
            }
        }

        viewModel.listOfCityEntity.observe(viewLifecycleOwner){
            val layoutManager = LinearLayoutManager(context)
            val rvCityList:RecyclerView = binding.recyclerView
            rvCityList.layoutManager = layoutManager
            val recyclerViewAdapter = RecyclerViewAdapter()
            val rezult = it.map {
                CityEntityItem(it.cityName)
            }
            Log.d("Tesing","Кладем резалт:")
            recyclerViewAdapter.update(rezult)
        }
    }



}