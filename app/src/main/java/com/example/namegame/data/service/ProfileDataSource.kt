package com.example.namegame.data.service

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.namegame.data.entity.Profile
import java.io.IOException

class ProfileDataSource {
    private val _downloadedProfiles = MutableLiveData<List<Profile>>()
    val downloadedProfiles: LiveData<List<Profile>> get() = _downloadedProfiles

    suspend fun fetchProfiles() {
        try {
            val fetchedProfile = ProfileApi.invoke().getProfiles()
            _downloadedProfiles.postValue(fetchedProfile)
        } catch (e: IOException) {
            Log.e("Error", "No internet connection", e)
        }
    }
}