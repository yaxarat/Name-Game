package com.example.namegame.database.repository.service

import com.example.namegame.database.entity.Profile
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface ProfileApi {

    @GET("profiles")
    suspend fun getProfiles(): List<Profile>
}