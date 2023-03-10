package com.example.weatherapp.presentation.addCityScreen

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentCityListBinding
import com.example.weatherapp.utils.LiveDataUtils.liveDataOwner
import org.koin.androidx.viewmodel.ext.android.viewModel

class CityListFragment : Fragment(R.layout.fragment_city_list) {

    private val binding by viewBinding(FragmentCityListBinding::bind)
    private val viewModel by viewModel<CityListViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager =
            layoutManager // TODO: тебе надо каждый раз устанавливать и LauoutManager и сам Адаптер?
        val recyclerViewAdapter = RecyclerViewAdapter()
        binding.recyclerView.adapter = recyclerViewAdapter

        viewModel.isLoading.observe(liveDataOwner) {
            // TODO: заменить на progressBar.isVisible = it (готово)
            when (it) {
                true -> binding.progressBar.isVisible = it
                false -> binding.progressBar.isVisible = it
            }
        }

        viewModel.listOfCityEntity.observe(liveDataOwner) {

            val cityItem = it.map { city->
                CityItem(city.cityName, city.isLiked){ isLiked ->
                    if (isLiked){
                        //TODO("Добавить в бд")
                    } else {
                        //TODO("Удалить из бд)
                    }
                }
            }
            recyclerViewAdapter.update(cityItem)
        }


    }

    // TODO: вместо viewLifecycleOwner используй написанное liveDataOwner (готоово)

    // TODO: зачем ты в этот метод передаешь view: View но не используешь их? (готово)

}