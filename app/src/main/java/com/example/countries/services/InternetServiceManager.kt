package com.example.countries.services

import android.content.Context
import android.net.ConnectivityManager

object InternetServiceManager {
    @Suppress("DEPRECATION")
    fun isOnline(context: Context?): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        val networkInfo = connectivityManager?.activeNetworkInfo
        return networkInfo?.isConnected ?: false
    }
}