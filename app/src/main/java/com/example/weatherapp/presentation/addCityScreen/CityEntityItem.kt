package com.example.weatherapp.presentation.addCityScreen

import android.widget.BaseAdapter
import com.example.weatherapp.R
import com.example.weatherapp.databinding.CityItemBinding
import com.example.weatherapp.databinding.FragmentAddCityRecyclerViewBinding

class CityEntityItem(val cityName:String):RecyclerViewAdapter.Item {
    override fun getItemViewType(): Int = R.layout.city_item



    override fun onBindViewHolder(holder: RecyclerViewAdapter.Holder, position: Int) {
        holder.itemView.apply {
            val binding = CityItemBinding.bind(this)
            binding.city.text = cityName

            kotlin.runCatching {
            }
        }
    }
}