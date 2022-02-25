package com.example.countries.utils

import androidx.room.TypeConverter
import com.google.gson.Gson

class StringTypeConverters {

    @TypeConverter
    fun listToJson(value: List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value,Array<String>::class.java).toList()
}