package com.example.weatherapp.presentation.addCityScreen

import androidx.core.content.ContextCompat
import com.example.weatherapp.R
import com.example.weatherapp.databinding.CityItemBinding

/**
 * TODO:
 * 1. переименовать класс. причем тут Ентити?
 * 2. что такое isLikedCity ? почему он var ?
 */
class CityItem(
    private val cityName: String,
    private var isLiked: Boolean,
    private val isLikedChangeListener: (isLiked: Boolean) -> Unit
) : RecyclerViewAdapter.Item {
    override fun getItemViewType(): Int = R.layout.city_item
    override fun onBindViewHolder(holder: RecyclerViewAdapter.Holder, position: Int) {
        holder.itemView.apply {
            val binding = CityItemBinding.bind(this)
            binding.city.text = cityName
            fun setIsLiked(value: Boolean) {
                isLiked = value
                binding.isLiked.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        if (isLiked) R.drawable.ic_is_liked else R.drawable.ic_is_not_liked
                    )
                )
            }
            setIsLiked(isLiked)
            binding.isLiked.setOnClickListener {
                setIsLiked(!isLiked)
                isLikedChangeListener(isLiked)
            }
        }
    }
}
