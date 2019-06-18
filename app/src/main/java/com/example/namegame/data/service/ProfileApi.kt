package com.example.namegame.data.service

import com.example.namegame.data.entity.Profile
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface ProfileApi {

    @GET("profiles")
    suspend fun getProfiles(): List<Profile>

    companion object {
        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor): ProfileApi {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://willowtreeapps.com/api/v1.0/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(ProfileApi::class.java)
        }
    }
}