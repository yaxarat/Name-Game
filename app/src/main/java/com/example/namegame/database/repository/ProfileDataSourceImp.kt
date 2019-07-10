package com.example.namegame.database.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.namegame.MainApp
import com.example.namegame.database.entity.Profile
import com.example.namegame.database.repository.service.ProfileApi
import com.example.namegame.database.repository.service.ProfileDataSource
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileDataSourceImp @Inject constructor(var profileApi: ProfileApi): ProfileDataSource {
    private val _downloadedProfiles = MutableLiveData<List<Profile>>()
    override val downloadedProfiles: LiveData<List<Profile>> get() = _downloadedProfiles

    override suspend fun fetchProfiles() {
        try {
            val fetchedProfile = profileApi.getProfiles()
            _downloadedProfiles.postValue(fetchedProfile)
        } catch (e: IOException) {
            Log.e("Error", "No internet connection", e)
        }
    }
}