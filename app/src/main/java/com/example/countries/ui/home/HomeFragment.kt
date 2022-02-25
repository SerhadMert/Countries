package com.example.countries.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.countries.R
import com.example.countries.base.BaseFragment
import com.example.countries.data.entity.countries.CountriesData
import com.example.countries.data.entity.countrydetail.Data
import com.example.countries.databinding.FragmentHomeBinding
import com.example.countries.ui.favorites.IFavoriteItem
import com.example.countries.utils.Resource
import com.example.countries.utils.setActionBarTitle
import com.example.countries.utils.showDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    IFavoriteItem {
    private val adapter by lazy { CountryListAdapter(this) }
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
                    response.data?.data?.let { checkFavorite(it) }
                }
                Resource.Status.ERROR -> {
                    hideLoading()
                    showDialog(requireContext(), message = "${response.message}")
                }
            }
        }
    }

    private fun checkFavorite(list: List<CountriesData>) {
        val localFavoriteList = viewModel.getFavorites()
        for (item in localFavoriteList) {
            list.find {
                it.code == item.code
            }?.isFavorite = true
        }
        setData(list)
    }

    private fun setData(list: List<CountriesData>) {
        adapter.setData(list)
    }

    override fun favoriteItem(item: CountriesData, position: Int) {
        if (item.isFavorite) {
            viewModel.addToFavorites(item)
        } else {
            viewModel.deleteFromFavorites(item)
        }
    }
}
