package com.example.namegame.data.repository

import androidx.lifecycle.LiveData
import com.example.namegame.data.entity.Profile
import com.example.namegame.data.service.ProfileDao
import com.example.namegame.data.service.ProfileDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime

class ProfileRepository(private val profileDao: ProfileDao) {
    private val profileDataSource = ProfileDataSource()
    private var now = ZonedDateTime.now()
    private var lastFetchedTime = now.minusMinutes(31)

    init {
        profileDataSource.downloadedProfiles.observeForever {
            newProfiles -> persistFetchedProfile(newProfiles)
        }
    }
    suspend fun getProfiles(): LiveData<List<Profile>> {
        return withContext(Dispatchers.IO) {
            initProfileData()
            return@withContext profileDao.getSixProfiles()
        }
    }

    suspend fun getAllProfiles(): LiveData<List<Profile>> {
        return withContext(Dispatchers.IO) {
            initProfileData()
            return@withContext profileDao.getAllProfiles()
        }
    }

    private fun persistFetchedProfile(fetchedProfile: List<Profile>) {
        GlobalScope.launch(Dispatchers.IO) {
            for (profile in fetchedProfile) {
                profileDao.upsert(profile)
            }
        }
    }

    private suspend fun initProfileData() {
        if (lastFetchedTime.isBefore(now.minusMinutes(30))) {
            lastFetchedTime = now
            profileDataSource.fetchProfiles()
        }
    }
}