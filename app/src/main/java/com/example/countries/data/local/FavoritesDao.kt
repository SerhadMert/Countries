package com.example.countries.data.local

import androidx.room.*
import com.example.countries.data.entity.countries.CountriesData

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM countries")
    fun getFavorites(): List<CountriesData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToFavorites(countries: CountriesData)

    @Delete
    fun deleteFromFavorites(countries: CountriesData)
}