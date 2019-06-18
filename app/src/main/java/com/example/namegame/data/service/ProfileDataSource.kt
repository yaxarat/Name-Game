package com.example.namegame.data.service

import androidx.lifecycle.LiveData
import com.example.namegame.data.entity.Profile

interface ProfileDataSource {
    val downloadedProfiles: LiveData<List<Profile>>

    suspend fun fetchProfiles()
}