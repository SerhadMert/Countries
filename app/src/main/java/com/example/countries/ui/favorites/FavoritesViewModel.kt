package com.example.countries.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countries.data.entity.countries.CountriesData
import com.example.countries.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    fun getFavorites() = repository.getFavorites()

    fun deleteFromFavorites(countriesData: CountriesData){
        viewModelScope.launch(IO) { repository.deleteFromFavorites(countriesData) }
    }
}