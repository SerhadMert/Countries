package com.example.countries.data.entity.countries


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "countries")
@Parcelize
data class CountriesData(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("code")
    @ColumnInfo val code: String,
    @SerializedName("currencyCodes")
    @ColumnInfo val currencyCodes: List<String>?,
    @SerializedName("name")
    @ColumnInfo val name: String?,
    @SerializedName("wikiDataId")
    @ColumnInfo val wikiDataId: String?,
    @ColumnInfo var isFavorite: Boolean = false
):Parcelable