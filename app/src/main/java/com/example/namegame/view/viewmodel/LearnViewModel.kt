package com.example.namegame.view.viewmodel

import androidx.lifecycle.ViewModel
import com.example.namegame.database.entity.Profile
import com.example.namegame.database.repository.ProfileRepository
import io.reactivex.Single

class LearnViewModel (private val profileRepository: ProfileRepository) : ViewModel() {
    fun getAllProfiles(): Single<List<Profile>> {
        return profileRepository.getAllProfiles()
    }
}