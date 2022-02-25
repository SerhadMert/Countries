package com.example.countries.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.countries.data.entity.countries.CountriesData
import com.example.countries.utils.StringTypeConverters

@Database(entities = [CountriesData::class], version = 1)
@TypeConverters(StringTypeConverters::class)
abstract class RoomDB : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
}