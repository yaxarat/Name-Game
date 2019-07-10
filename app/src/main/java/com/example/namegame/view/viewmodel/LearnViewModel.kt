package com.example.namegame.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.namegame.MainApp
import com.example.namegame.database.entity.Profile
import com.example.namegame.database.repository.ProfileRepository
import com.example.namegame.utility.delegate.lazyDeferred
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class LearnViewModel (private val profileRepository: ProfileRepository) : ViewModel() {
    var profiles: Deferred<LiveData<List<Profile>>>

    init {
        val profile by lazyDeferred {profileRepository.getAllProfiles()}
        profiles = profile
    }
}