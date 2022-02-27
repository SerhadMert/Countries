package com.example.countries.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.BufferedReader
import java.io.InputStreamReader

val gson: Gson = GsonBuilder()
    .create()

inline fun <reified T> parseFileAsResponse(fileName: String): T {
    return gson.fromJson(gson.getResourceReader(fileName),typeOf<T>())
}

fun Gson.getResourceReader(fileName: String): BufferedReader {
    val resource = javaClass.classLoader!!.getResourceAsStream(fileName)
    return BufferedReader(InputStreamReader(resource, Charsets.UTF_8))
}