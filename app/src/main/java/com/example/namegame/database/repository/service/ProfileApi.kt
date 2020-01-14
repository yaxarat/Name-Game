package com.example.namegame.database.repository.service

import com.example.namegame.database.entity.Profile
import io.reactivex.Single
import retrofit2.http.GET

interface ProfileApi {

    @GET("profiles")
    fun getProfiles(): Single<List<Profile>>
}