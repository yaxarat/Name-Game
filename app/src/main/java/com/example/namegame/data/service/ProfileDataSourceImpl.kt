package com.example.namegame.data.service

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.namegame.data.entity.Profile

class ProfileDataSourceImpl(private val profileApi: ProfileApi) : ProfileDataSource {
    private val _downloadedProfiles = MutableLiveData<List<Profile>>()
    override val downloadedProfiles: LiveData<List<Profile>> get() = _downloadedProfiles

    override suspend fun fetchProfiles() {
        try {
            val fetchedProfile = profileApi.getProfiles()
            _downloadedProfiles.postValue(fetchedProfile)
        } catch (e: NoConnectivityException) {
            Log.e("Error", "No internet connection", e)
        }
    }
}