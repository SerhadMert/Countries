package com.example.countries.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.countries.data.entity.countries.Countries
import com.example.countries.data.entity.countries.CountriesData
import com.example.countries.data.repository.Repository
import com.example.countries.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    fun getCountries(offset:Int): LiveData<Resource<Countries>> {
        return repository.getCountries(offset)
    }

    fun getFavorites() = repository.getFavorites()

    fun addToFavorites(countries:CountriesData){
        viewModelScope.launch { repository.addToFavorites(countries) }
    }

    fun deleteFromFavorites(countries: CountriesData){
        viewModelScope.launch { repository.deleteFromFavorites(countries) }
    }
}