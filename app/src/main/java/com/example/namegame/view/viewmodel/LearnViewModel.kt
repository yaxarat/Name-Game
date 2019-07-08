package com.example.namegame.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.namegame.data.entity.Profile
import com.example.namegame.data.repository.ProfileRepository
import com.example.namegame.utility.delegate.lazyDeferred
import kotlinx.coroutines.Deferred

class LearnViewModel(profileRepository: ProfileRepository) : ViewModel() {
    private val repository = profileRepository
    var profiles: Deferred<LiveData<List<Profile>>>

    init {
        val profile by lazyDeferred {repository.getAllProfiles()}
        profiles = profile
    }
}