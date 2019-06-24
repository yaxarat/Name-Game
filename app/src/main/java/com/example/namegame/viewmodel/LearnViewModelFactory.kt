package com.example.namegame.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.namegame.data.repository.ProfileRepository

class LearnViewModelFactory(private val profileRepository: ProfileRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LearnViewModel(profileRepository) as T
    }
}