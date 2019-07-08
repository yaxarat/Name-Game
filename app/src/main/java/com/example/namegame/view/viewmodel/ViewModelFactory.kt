package com.example.namegame.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.namegame.data.database.ProfileDatabase
import com.example.namegame.data.repository.ProfileRepository
import com.example.namegame.data.service.ProfileDao

@Suppress("UNCHECKED_CAST")
class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    private lateinit var profileRepository: ProfileRepository

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            GameViewModel::class.java -> GameViewModel(profileRepository) as T
            LearnViewModel::class.java -> LearnViewModel(profileRepository) as T
            else -> throw RuntimeException("Invalid viewmodel class: $modelClass")
        }
    }
}