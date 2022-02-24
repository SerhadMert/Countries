package com.example.countries.data.entity.countries


import com.google.gson.annotations.SerializedName

data class Countries(
    @SerializedName("data")
    val `data`: List<Data>?,
    @SerializedName("links")
    val links: List<Link>?,
    @SerializedName("metadata")
    val metadata: Metadata?
)