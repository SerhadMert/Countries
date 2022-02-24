package com.example.countries.data.entity.countries


import com.google.gson.annotations.SerializedName

data class CountriesData(
    @SerializedName("code")
    val code: String?,
    @SerializedName("currencyCodes")
    val currencyCodes: List<String>?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("wikiDataId")
    val wikiDataId: String?
)