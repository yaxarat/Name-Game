package com.example.namegame.view.viewmodel

import androidx.lifecycle.ViewModel
import com.example.namegame.database.entity.Profile
import com.example.namegame.database.repository.ProfileRepository
import io.reactivex.Single

class GameViewModel (private val profileRepository: ProfileRepository) : ViewModel() {
    var clickable = BooleanArray(6) {true}
    var answerIndex = 0
    var score = 0
    var attempt = 0

    fun newRound(): Single<List<Profile>> {
        answerIndex = (0 .. 5).random()
        clickable.fill(true)
        return profileRepository.getProfiles()
    }
}
