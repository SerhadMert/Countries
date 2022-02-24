package com.example.countries.data.entity.countries


import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("href")
    val href: String?,
    @SerializedName("rel")
    val rel: String?
)