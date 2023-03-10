package com.example.weatherapp.presentation.addCityScreen

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weatherapp.R
import com.example.weatherapp.databinding.CityItemBinding
import com.example.weatherapp.databinding.FragmentAddCityRecyclerViewBinding
import com.example.weatherapp.domain.entities.CityEntity
import com.example.weatherapp.utils.LiveDataUtils.liveDataOwner
import com.example.weatherapp.utils.logInfo
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecyclerViewFragment : Fragment(R.layout.fragment_add_city_recycler_view) {

    private val binding by viewBinding(FragmentAddCityRecyclerViewBinding::bind)

    private val viewModel by viewModel<RecyclerViewViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        val rvField: RecyclerView =
            binding.recyclerView // TODO: зачем брать в отдельную переменную?
        rvField.layoutManager =
            layoutManager // TODO: тебе надо каждый раз устанавливать и LauoutManager и сам Адаптер?
        val recyclerViewAdapter = RecyclerViewAdapter()
        rvField.adapter = recyclerViewAdapter


        observeViewModel(recyclerViewAdapter)
    }

    // TODO: вместо viewLifecycleOwner используй написанное liveDataOwner (готоово)

    // TODO: зачем ты в этот метод передаешь view: View но не используешь их? (готово)
    private fun observeViewModel(recyclerViewAdapter: RecyclerViewAdapter) {
        // TODO: зачем создаешь переменную? (готово)
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
}