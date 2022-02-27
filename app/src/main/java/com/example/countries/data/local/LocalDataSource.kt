package com.example.countries.data.local

import com.example.countries.data.entity.countries.CountriesData
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val favoritesDao: FavoritesDao) {

    fun getFavorites(): List<CountriesData>{
        return favoritesDao.getFavorites()
    }

    suspend fun addToFavorites(countries: CountriesData){
        favoritesDao.addToFavorites(countries)
    }

    suspend fun deleteFromFavorites(countries: CountriesData){
        favoritesDao.deleteFromFavorites(countries)
    }
}