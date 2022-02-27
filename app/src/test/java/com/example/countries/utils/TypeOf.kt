package com.example.countries.utils

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

inline fun <reified T> typeTokenOf(): TypeToken<T> {
    return object : TypeToken<T>() {}
}

inline fun <reified T> typeOf(): Type {
    return typeTokenOf<T>().type
}