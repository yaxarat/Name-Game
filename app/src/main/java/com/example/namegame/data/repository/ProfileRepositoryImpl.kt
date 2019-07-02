package com.example.namegame.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.namegame.data.entity.Profile
import com.example.namegame.data.service.ProfileDao
import com.example.namegame.data.service.ProfileDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime

class ProfileRepositoryImpl(private val profileDao: ProfileDao, private val profileDataSource: ProfileDataSource) : ProfileRepository {
    private var lastFetchedTime = ZonedDateTime.now().minusMinutes(11)
    init {
        profileDataSource.downloadedProfiles.observeForever {
            newProfiles -> persistFetchedProfile(newProfiles)
        }
    }
    override suspend fun getProfiles(): LiveData<List<Profile>> {
        return withContext(Dispatchers.IO) {
            initProfileData()
            return@withContext profileDao.getSixProfiles()
        }
    }

    override suspend fun getAllProfiles(): LiveData<List<Profile>> {
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
        if (isFetchNeeded(lastFetchedTime)) {
            Log.d("tag", "fetched")
            lastFetchedTime = ZonedDateTime.now()
            profileDataSource.fetchProfiles()
        } else {
            Log.d("tag", "notFetched")
        }
    }

    private fun isFetchNeeded(lastFetchedTime: ZonedDateTime): Boolean {
        return lastFetchedTime.isBefore(ZonedDateTime.now().minusMinutes(10))
    }
}