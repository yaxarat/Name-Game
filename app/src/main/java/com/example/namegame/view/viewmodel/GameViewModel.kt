package com.example.namegame.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.namegame.database.entity.Profile
import com.example.namegame.database.repository.ProfileRepository
import com.example.namegame.utility.delegate.lazyDeferred
import kotlinx.coroutines.Deferred

class GameViewModel(private val profileRepository: ProfileRepository) : ViewModel() {
    lateinit var profiles: Deferred<LiveData<List<Profile>>>
    var clickable = BooleanArray(6) {true}
    var answerIndex = 0
    var score = 0
    var attempt = 0

    init {
        newRound()
    }

    fun newRound() {
        val profile by lazyDeferred {profileRepository.getProfiles()}
        profiles = profile
        answerIndex = (0 .. 5).random()
        clickable.fill(true)
    }
}
