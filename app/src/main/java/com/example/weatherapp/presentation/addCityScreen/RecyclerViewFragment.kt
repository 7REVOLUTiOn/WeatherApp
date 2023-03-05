package com.example.weatherapp.presentation.addCityScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.databinding.FragmentAddCityRecyclerViewBinding
import com.example.weatherapp.di.viewModel

class RecyclerViewFragment : Fragment() {

    private var _binding: FragmentAddCityRecyclerViewBinding? = null
    private val binding get() = _binding!!

    //val viewModel by viewModel<RecyclerViewViewModel>()




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddCityRecyclerViewBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        observeViewModel(view)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun observeViewModel(view: View){
        val progressBar:ProgressBar = binding.progressBar


    }
}