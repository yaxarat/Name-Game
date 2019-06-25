package com.example.namegame.utility

import android.content.Context
import android.net.ConnectivityManager

class Network {
    companion object {
        fun isOnline(appContext: Context): Boolean {
            val online = (appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetwork
            return online != null
        }
    }
}