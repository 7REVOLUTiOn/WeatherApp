package com.example.weatherapp.presentation.addCityScreen

import android.util.Log
import androidx.core.content.ContextCompat
import com.example.weatherapp.R
import com.example.weatherapp.databinding.CityItemBinding
import com.example.weatherapp.domain.entities.CityEntity

/**
 * TODO:
 * 1. переименовать класс. причем тут Ентити?
 * 2. что такое isLikedCity ? почему он var ?
 */
class CityItem(
    val cityName: String,
    isLikedCity: Boolean,
    var listener:( holder: RecyclerViewAdapter.Holder) -> Unit
) : RecyclerViewAdapter.Item {
    override fun getItemViewType(): Int = R.layout.city_item
    override fun onBindViewHolder(holder: RecyclerViewAdapter.Holder, position: Int) {
        holder.itemView.apply {
            val binding = CityItemBinding.bind(this)
            binding.city.text = cityName
            binding.isLiked.setOnClickListener {
                listener(holder)
            }
        }
    }
}
