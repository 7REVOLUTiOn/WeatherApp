package com.example.weatherapp.presentation.addCityScreen

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentAddCityRecyclerViewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecyclerViewFragment : Fragment(R.layout.fragment_add_city_recycler_view) {

    private val binding by viewBinding(FragmentAddCityRecyclerViewBinding::bind)

    private val viewModel by viewModel<RecyclerViewViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel(view)
    }

    private fun observeViewModel(view: View) {
        val progressBar = binding.progressBar
        viewModel.isLoading.observe(viewLifecycleOwner){
            when(it){
                true -> progressBar.isVisible = true
                false -> progressBar.isVisible = false
            }
        }

        viewModel.listOfCityEntity.observe(viewLifecycleOwner){
            val layoutManager = LinearLayoutManager(context)
            val rvField:RecyclerView = binding.recyclerView
            rvField.layoutManager = layoutManager
            val recyclerViewAdapter = RecyclerViewAdapter()
            rvField.adapter = recyclerViewAdapter
            val cityItem = it.map {
                CityEntityItem(it.cityName,it.isLiked)
            }
            recyclerViewAdapter.update(cityItem)
        }
    }
}