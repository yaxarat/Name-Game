package com.example.namegame.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.namegame.data.entity.Profile
import com.example.namegame.data.repository.ProfileRepository
import com.example.namegame.delegate.lazyDeferred

class GameViewModel(profileRepository: ProfileRepository) : ViewModel() {
    private val repository = profileRepository
    val randomInt = (0..5).random()

    suspend fun getNewProfiles(): LiveData<List<Profile>> {
        val profile by lazyDeferred {repository.getProfiles()}
        return profile.await()
    }
}
