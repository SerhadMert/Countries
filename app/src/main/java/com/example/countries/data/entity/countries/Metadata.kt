package com.example.countries.data.entity.countries


import com.google.gson.annotations.SerializedName

data class Metadata(
    @SerializedName("currentOffset")
    val currentOffset: Int?,
    @SerializedName("totalCount")
    val totalCount: Int?
)