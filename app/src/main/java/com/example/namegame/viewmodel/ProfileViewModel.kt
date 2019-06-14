package com.example.namegame.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.namegame.database.ProfileRepository
import com.example.namegame.database.ProfileRoomDatabase
import com.example.namegame.model.Headshot
import com.example.namegame.model.Profile

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ProfileRepository
    val allProfiles: List<Profile>

    init {
        val profileDao = ProfileRoomDatabase.getDatabase(application).profileDao()
        repository = ProfileRepository(profileDao)
        allProfiles = repository.allProfiles
    }

    fun getSixProfiles(): Array<Headshot?> {
        val images = arrayOfNulls<Headshot>(6)
        for (n in 0 .. 5) {
            images[n] = (allProfiles[n].headshot)
        }
        return images
    }
}