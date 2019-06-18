package com.example.namegame.viewmodel

import androidx.lifecycle.ViewModel;
import com.example.namegame.data.repository.ProfileRepository
import com.example.namegame.delegate.lazyDeferred

class GameViewModel(profileRepository: ProfileRepository) : ViewModel() {
    val profile by lazyDeferred {profileRepository.getProfiles()}
}
