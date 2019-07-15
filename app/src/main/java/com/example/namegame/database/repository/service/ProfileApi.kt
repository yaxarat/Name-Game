package com.example.namegame.database.repository.service

import com.example.namegame.database.entity.Profile
import io.reactivex.Observable
import retrofit2.http.GET

interface ProfileApi {

    @GET("profiles")
    fun getProfiles(): Observable<List<Profile>>
}