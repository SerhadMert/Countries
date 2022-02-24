package com.example.countries.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.countries.base.BaseFragment
import com.example.countries.databinding.FragmentHomeBinding
import com.example.countries.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel: HomeViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCountries().observe(viewLifecycleOwner) { response ->

            Log.d("response", response.toString())
            when (response.status) {
                Resource.Status.LOADING -> {
                    binding.apply {
                    }
                }
                Resource.Status.SUCCESS -> {
                    response.data?.data?.size
                    Log.d("CountriesList", "${response.data?.data?.size}")
                }
                Resource.Status.ERROR -> {
                    Log.d("CountriesList", "${response.message}")
                }
            }
        }
    }
}