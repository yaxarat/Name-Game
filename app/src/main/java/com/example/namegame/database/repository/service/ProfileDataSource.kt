package com.example.namegame.database.repository.service

import androidx.lifecycle.LiveData
import com.example.namegame.database.entity.Profile

interface ProfileDataSource {
    val downloadedProfiles: LiveData<List<Profile>>
    suspend fun fetchProfiles()
}