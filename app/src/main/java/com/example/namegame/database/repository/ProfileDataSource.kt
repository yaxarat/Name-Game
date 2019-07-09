package com.example.namegame.database.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.namegame.database.entity.Profile
import com.example.namegame.database.repository.service.ProfileApi
import com.example.namegame.database.repository.service.ProfileDataSource_Imp
import java.io.IOException

class ProfileDataSource: ProfileDataSource_Imp {
    private val _downloadedProfiles = MutableLiveData<List<Profile>>()
    override val downloadedProfiles: LiveData<List<Profile>> get() = _downloadedProfiles

    suspend fun fetchProfiles() {
        try {
            val fetchedProfile = ProfileApi.invoke().getProfiles()
            _downloadedProfiles.postValue(fetchedProfile)
        } catch (e: IOException) {
            Log.e("Error", "No internet connection", e)
        }
    }
}