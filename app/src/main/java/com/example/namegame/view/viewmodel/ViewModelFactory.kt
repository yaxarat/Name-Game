package com.example.namegame.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.namegame.database.repository.ProfileRepository
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class ViewModelFactory @Inject constructor(var profileRepository: ProfileRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            GameViewModel::class.java -> GameViewModel(profileRepository) as T
            LearnViewModel::class.java -> LearnViewModel(profileRepository) as T
            else -> throw RuntimeException("Invalid viewmodel class: $modelClass")
        }
    }
}