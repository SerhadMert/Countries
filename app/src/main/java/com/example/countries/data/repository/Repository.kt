package com.example.countries.data.repository

import com.example.countries.data.remote.RemoteDataSource
import com.example.countries.utils.performNetworkOperation
import javax.inject.Inject

class Repository @Inject constructor(private var remoteDataSource: RemoteDataSource){

    fun getCountries() = performNetworkOperation {
        remoteDataSource.getCountries()
    }

    fun getCountryDetailBCode(code: String) = performNetworkOperation {
        remoteDataSource.getCountryDetailByCode(code)
    }
}