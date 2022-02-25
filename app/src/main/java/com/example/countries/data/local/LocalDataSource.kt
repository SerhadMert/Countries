package com.example.countries.data.local

import com.example.countries.data.entity.countries.CountriesData
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val favoritesDao: FavoritesDao) {

    fun getFavorites(): List<CountriesData>{
        return favoritesDao.getFavorites()
    }

    fun addToFavorites(countries: CountriesData){
        favoritesDao.addToFavorites(countries)
    }

    fun deleteFromFavorites(countries: CountriesData){
        favoritesDao.deleteFromFavorites(countries)
    }
}