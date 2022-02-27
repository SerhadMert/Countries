package com.example.countries.data.remote

import com.example.countries.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService): BaseDataSource(){

    suspend fun getCountries(offset:Int) = getResult {
        apiService.getCountries(offset)
    }

    suspend fun getCountryDetailByCode(code: String) = getResult {
        apiService.getCountryDetailByCode(code)
    }
}