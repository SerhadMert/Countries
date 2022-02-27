package com.example.countries.data.local

import androidx.room.*
import com.example.countries.data.entity.countries.CountriesData
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM countries")
    fun getFavorites(): List<CountriesData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(countries: CountriesData)

    @Delete
    suspend fun deleteFromFavorites(countries: CountriesData)
}