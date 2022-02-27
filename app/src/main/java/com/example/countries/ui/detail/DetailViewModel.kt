package com.example.countries.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countries.data.entity.countries.CountriesData
import com.example.countries.data.entity.countrydetail.CountryDetail
import com.example.countries.data.repository.Repository
import com.example.countries.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    fun getCountryDetailByCode(code: String): LiveData<Resource<CountryDetail>> {
        return repository.getCountryDetailBCode(code)
    }

    fun getFavorites() = repository.getFavorites()

    fun addToFavorites(countriesData: CountriesData) {
        viewModelScope.launch(IO) { repository.addToFavorites(countriesData) }
    }

    fun deleteFromFavorites(countriesData: CountriesData) {
        viewModelScope.launch(IO) { repository.deleteFromFavorites(countriesData) }
    }
}