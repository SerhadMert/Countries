package com.example.countries.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.countries.data.entity.countries.Countries
import com.example.countries.data.entity.countries.CountriesData
import com.example.countries.data.repository.Repository
import com.example.countries.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    fun getCountries(): LiveData<Resource<Countries>> {
        return repository.getCountries()
    }

    fun getFavorites() = repository.getFavorites()

    fun addToFavorites(countries:CountriesData) = repository.addToFavorites(countries)

    fun deleteFromFavorites(countries: CountriesData) = repository.deleteFromFavorites(countries)
}