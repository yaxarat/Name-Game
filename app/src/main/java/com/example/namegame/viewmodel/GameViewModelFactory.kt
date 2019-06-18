package com.example.namegame.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.namegame.data.repository.ProfileRepository

@Suppress("UNCHECKED_CAST")
class GameViewModelFactory(private val profileRepository: ProfileRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GameViewModel(profileRepository) as T
    }
}