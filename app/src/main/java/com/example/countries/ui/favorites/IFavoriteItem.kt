package com.example.countries.ui.favorites

import com.example.countries.data.entity.countries.CountriesData

interface IFavoriteItem {
    fun favoriteItem(item:CountriesData,position:Int)
}