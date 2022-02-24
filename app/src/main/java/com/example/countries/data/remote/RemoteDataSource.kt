package com.example.countries.data.remote

import com.example.countries.utils.BaseDataSource
import com.example.countries.utils.Constants
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService): BaseDataSource(){

    suspend fun getCountries() = getResult {
        apiService.getCountries(Constants.LIMIT)
    }

    suspend fun getCountryDetailByCode(code: String) = getResult {
        apiService.getCountryDetailByCode(code)
    }
}