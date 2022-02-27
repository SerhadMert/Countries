package com.example.countries.data.remote

import com.example.countries.data.entity.countries.Countries
import com.example.countries.data.entity.countrydetail.CountryDetail
import com.example.countries.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.END_POINT_COUNTRIES)
    suspend fun getCountries(@Query("offset") offset:Int):Response<Countries>

    @GET(Constants.END_POINT_COUNTRY_DETAIL)
    suspend fun getCountryDetailByCode(@Path("code") code:String): Response<CountryDetail>
}