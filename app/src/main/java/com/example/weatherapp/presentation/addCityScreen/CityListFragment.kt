package com.example.weatherapp.presentation.addCityScreen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentCityListBinding
import com.example.weatherapp.utils.LiveDataUtils.liveDataOwner
import com.example.weatherapp.utils.UiExceptionText
import org.koin.androidx.viewmodel.ext.android.viewModel

class CityListFragment() : Fragment(R.layout.fragment_city_list) {

    private val binding by viewBinding(FragmentCityListBinding::bind)
    private val viewModel by viewModel<CityListViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO: тебе надо каждый раз устанавливать и LauoutManager и сам Адаптер?
        binding.recyclerView.layoutManager =
            LinearLayoutManager(context)
        val recyclerViewAdapter = RecyclerViewAdapter()
        binding.recyclerView.adapter = recyclerViewAdapter

        viewModel.isLoading.observe(liveDataOwner) {
            binding.progressBar.isVisible = it
        }

        //todo: посмотреть text вотчеры
        binding.editText.doAfterTextChanged {
            viewModel.setSearchText(it.toString())
        }

        viewModel.searchText.observe(liveDataOwner) {
            if (binding.editText.text.toString() != it) {
                binding.editText.setText(it)
            }
        }

        viewModel.listOfCityEntity.observe(liveDataOwner) {
            val cityItem = it.map { city ->
                CityItem(city.cityName, city.isLiked) { isLiked ->
                    if (isLiked) {
                        viewModel.addCityToFavorite(city)
                    } else {
                        viewModel.deleteCityFromFavorite(city)
                    }
                }
            }
            recyclerViewAdapter.update(cityItem)
        }

        viewModel.error.observe(liveDataOwner) {
            val text = UiExceptionText.returnStringByException(it, requireContext())
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }

    }

    // TODO: вместо viewLifecycleOwner используй написанное liveDataOwner (готоово)

}