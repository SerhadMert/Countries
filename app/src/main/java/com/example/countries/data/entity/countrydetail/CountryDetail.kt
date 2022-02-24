package com.example.countries.data.entity.countrydetail


import com.google.gson.annotations.SerializedName

data class CountryDetail(
    @SerializedName("data")
    val `data`: Data?
)