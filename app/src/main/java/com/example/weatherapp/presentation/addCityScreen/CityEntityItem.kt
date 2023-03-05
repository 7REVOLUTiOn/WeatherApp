package com.example.weatherapp.presentation.addCityScreen

import androidx.core.content.ContextCompat
import com.example.weatherapp.R
import com.example.weatherapp.databinding.CityItemBinding

class CityEntityItem(val cityName:String,var isLikedCity:Boolean):RecyclerViewAdapter.Item {
    override fun getItemViewType(): Int = R.layout.city_item


    override fun onBindViewHolder(holder: RecyclerViewAdapter.Holder, position: Int) {
        holder.itemView.apply {
            val binding = CityItemBinding.bind(this)
            binding.city.text = cityName
            binding.isLiked.setOnClickListener {
                if (isLikedCity == false){
                    binding.isLiked.setImageDrawable(
                        ContextCompat.getDrawable(context, R.drawable.ic_is_liked)
                    )
                    isLikedCity = true
                }
                if (isLikedCity == true){
                    binding.isLiked.setImageDrawable(
                        ContextCompat.getDrawable(context, R.drawable.ic_is_liked)
                    )
                    isLikedCity = false
                }

            }
        }
    }
}