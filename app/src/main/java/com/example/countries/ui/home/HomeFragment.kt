package com.example.countries.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.countries.R
import com.example.countries.base.BaseFragment
import com.example.countries.databinding.FragmentHomeBinding
import com.example.countries.utils.Resource
import com.example.countries.utils.setActionBarTitle
import com.example.countries.utils.showDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val adapter by lazy { CountryListAdapter() }
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
        getCountries()
        setActionBarTitle(getString(R.string.app_name))
    }

    private fun initRV() {
        binding.rvCountries.adapter = adapter
    }

    private fun getCountries() {
        viewModel.getCountries().observe(viewLifecycleOwner) { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    showLoading()
                }
                Resource.Status.SUCCESS -> {
                    hideLoading()
                    adapter.setData(response.data?.data ?: listOf())
                }
                Resource.Status.ERROR -> {
                    hideLoading()
                    showDialog(requireContext(), message = "${response.message}")
                }
            }
        }
    }
}