package com.example.namegame.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.namegame.data.entity.Profile
import com.example.namegame.data.repository.ProfileRepository
import com.example.namegame.delegate.lazyDeferred
import kotlinx.coroutines.Deferred

class GameViewModel(profileRepository: ProfileRepository) : ViewModel() {
    private val repository = profileRepository
    lateinit var profiles: Deferred<LiveData<List<Profile>>>
    var answerIndex = 0
    var score = 0

    init {
        getNewProfiles()
        getNewAnswerIndex()
    }

    fun getNewProfiles() {
        val profile by lazyDeferred {repository.getProfiles()}
        profiles = profile
    }

    fun getNewAnswerIndex(){
        val randomInt = (0..5).random()
        answerIndex = randomInt
    }
}
