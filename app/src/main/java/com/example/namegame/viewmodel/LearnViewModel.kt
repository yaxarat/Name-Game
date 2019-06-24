package com.example.namegame.viewmodel

import android.view.animation.Transformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.namegame.data.entity.Profile
import com.example.namegame.data.repository.ProfileRepository
import com.example.namegame.delegate.lazyDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking

class LearnViewModel(profileRepository: ProfileRepository) : ViewModel() {
    private val repository = profileRepository
    lateinit var profiles: Deferred<LiveData<List<Profile>>>

    init {
        getAllProfiles()
    }

    fun getAllProfiles() {
        val profile by lazyDeferred {repository.getAllProfiles()}
        profiles = profile
    }
}