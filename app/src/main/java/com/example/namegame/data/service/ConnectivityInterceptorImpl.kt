package com.example.namegame.data.service

import android.content.Context
import com.example.namegame.utility.Network
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptorImpl(context: Context) : ConnectivityInterceptor {
    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!Network.isOnline(appContext)) {
            throw NoConnectivityException()
        } else {
            return chain.proceed(chain.request())
        }
    }
}