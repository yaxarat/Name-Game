package com.example.namegame.service

import com.example.namegame.data.Profile
import retrofit2.Call
import retrofit2.http.GET

interface ProfilesApi {

    @get:GET("profiles")
    val profiles: Call<List<Profile>>
}