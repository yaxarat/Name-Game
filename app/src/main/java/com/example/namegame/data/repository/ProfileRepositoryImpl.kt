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
    init {
        profileDataSource.downloadedProfiles.observeForever {
            newProfiles -> persistFetchedProfile(newProfiles)
        }
    }
    override suspend fun getProfiles(): LiveData<List<Profile>> {
        return withContext(Dispatchers.IO) {
            initProfileData()
            return@withContext profileDao.getFirstSixProfiles()
        }
    }

    private fun persistFetchedProfile(fetchedProfile: List<Profile>) {
        GlobalScope.launch(Dispatchers.IO) {
            for (profile in fetchedProfile) {
                Log.d("tag", "$profile")
                profileDao.upsert(profile)
            }
        }
    }

    private suspend fun initProfileData() {
        // TODO: fix date based fetch system
        if (isFetchNeeded(ZonedDateTime.now().minusDays(2))) {
            profileDataSource.fetchProfiles()
        }
    }

    private fun isFetchNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val aDayAgo = ZonedDateTime.now().minusDays(1)
        return lastFetchTime.isBefore(aDayAgo)
    }
}