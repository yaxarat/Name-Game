package com.example.namegame.data.repository

import androidx.lifecycle.LiveData
import com.example.namegame.data.entity.Profile

interface ProfileRepository {
    suspend fun getProfiles(): LiveData<List<Profile>>
    suspend fun getAllProfiles(): LiveData<List<Profile>>
}