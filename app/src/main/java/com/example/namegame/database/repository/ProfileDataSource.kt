package com.example.namegame.database.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.namegame.database.entity.Profile
import com.example.namegame.database.repository.service.ProfileApi
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileDataSource @Inject constructor(private val profileApi: ProfileApi) {
    private val mutableLiveProfiles = MutableLiveData<List<Profile>>()
    val downloadedProfiles: LiveData<List<Profile>> get() = mutableLiveProfiles

    suspend fun fetchProfiles() {
        try {
            val fetchedProfile = profileApi.getProfiles()
            mutableLiveProfiles.postValue(fetchedProfile)
        } catch (e: IOException) {
            Log.e("Error", "No internet connection", e)
        }
    }
}