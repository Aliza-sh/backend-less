package com.aliza.alizaparse.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build


object CheckConnection {

    fun provideCM(context: Context) =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun provideNR(): NetworkRequest = NetworkRequest.Builder().apply {
        addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        //Android M
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        //Android P
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
            addCapability(NetworkCapabilities.NET_CAPABILITY_FOREGROUND)
    }.build()
}