package com.example.namegame.database.repository

import android.annotation.SuppressLint
import android.util.Log
import com.example.namegame.database.entity.Profile
import com.example.namegame.database.repository.service.ProfileApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileDataSource @Inject constructor(private val profileApi: ProfileApi) {
    var downloadedProfiles: List<Profile> = emptyList()

    @SuppressLint("CheckResult") // TODO: check this warning
    fun fetchProfiles() {
        profileApi.getProfiles().subscribe(
            {profileList -> this.downloadedProfiles = profileList},
            {throwable -> Log.e("tag", "$throwable")}
        )
    }
}