package com.example.namegame.database

import androidx.annotation.WorkerThread
import com.example.namegame.model.Profile
import com.example.namegame.service.ProfileDao

class ProfileRepository(private val profileDao: ProfileDao) {
    val allProfiles: List<Profile> = profileDao.getProfiles()

    @WorkerThread
    suspend fun insert(profiles: List<Profile>) {
        profileDao.insert(profiles)
    }

    @WorkerThread
    suspend fun update(profiles: List<Profile>) {
        profileDao.update(profiles)
    }
}