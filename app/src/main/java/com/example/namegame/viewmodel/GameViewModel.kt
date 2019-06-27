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
    lateinit var clickable: HashMap<Int, Boolean>
    var answerIndex = 0
    var score = 0
    var attempt = 0

    init {
        newRound()
    }

    fun newRound() {
        val profile by lazyDeferred {repository.getProfiles()}
        val randomInt = (0..5).random()

        profiles = profile
        answerIndex = randomInt
        clickable = hashMapOf(
            0 to true,
            1 to true,
            2 to true,
            3 to true,
            4 to true,
            5 to true
        )
    }
}
