package com.example.countries.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.base.BaseFragment
import com.example.countries.data.entity.countries.CountriesData
import com.example.countries.databinding.FragmentHomeBinding
import com.example.countries.ui.favorites.IFavoriteItem
import com.example.countries.utils.Constants
import com.example.countries.utils.Resource
import com.example.countries.utils.showDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    IFavoriteItem {
    private val adapter by lazy { CountryListAdapter(this) }
    private val viewModel: HomeViewModel by viewModels()
    private var pageChanger = Constants.OFFSET
    private val countriesList = arrayListOf<CountriesData>()
    private var totalCount = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
        onScrollListener()
    }

    private fun initRV() {
        binding.rvCountries.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        countriesList.clear()
        pageChanger = Constants.OFFSET
        getCountries(pageChanger)
    }
    private fun getCountries(page:Int) {
        viewModel.getCountries(page).observe(viewLifecycleOwner) { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    showLoading()
                }
                Resource.Status.SUCCESS -> {
                    hideLoading()
                    totalCount = response.data?.metadata?.totalCount ?: 0
                    countriesList.addAll(response.data?.data ?: arrayListOf())
                    val scrollDistance = countriesList.size - (response.data?.data?.size ?: 0)
                    binding.rvCountries.scrollToPosition(scrollDistance)
                        checkFavorite()

                }
                Resource.Status.ERROR -> {
                    hideLoading()
                    showDialog(requireContext(), message = "${response.message}")
                }
            }
        }
    }

    private fun checkFavorite() {
        val localFavoriteList = viewModel.getFavorites()
        for (item in localFavoriteList) {
            countriesList.find {
                it.code == item.code
            }?.isFavorite = true
        }
        setData()
    }

    private fun setData() {
        adapter.setData(countriesList)
    }

    override fun favoriteItem(item: CountriesData, position: Int) {
        if (item.isFavorite) {
            viewModel.addToFavorites(item)
        } else {
            viewModel.deleteFromFavorites(item)
        }
    }
    private fun onScrollListener() {
        binding.rvCountries.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!binding.rvCountries.canScrollVertically(1) &&
                    newState == RecyclerView.SCROLL_STATE_IDLE &&
                    countriesList.size < totalCount
                ) {
                    pageChanger += 10
                    getCountries(pageChanger)
                }
            }
        })
    }
}
