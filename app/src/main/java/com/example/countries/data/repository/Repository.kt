package com.example.countries.data.repository

import com.example.countries.data.entity.countries.CountriesData
import com.example.countries.data.local.LocalDataSource
import com.example.countries.data.remote.RemoteDataSource
import com.example.countries.utils.performNetworkOperation
import javax.inject.Inject

class Repository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    fun getCountries() = performNetworkOperation {
        remoteDataSource.getCountries()
    }

    fun getCountryDetailBCode(code: String) = performNetworkOperation {
        remoteDataSource.getCountryDetailByCode(code)
    }

    fun getFavorites() = localDataSource.getFavorites()

    fun addToFavorites(countries: CountriesData) = localDataSource.addToFavorites(countries)

    fun deleteFromFavorites(countries: CountriesData) = localDataSource.deleteFromFavorites(countries)
}