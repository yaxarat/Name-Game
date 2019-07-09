package com.example.namegame.di

import android.app.Application
import com.example.namegame.database.repository.ProfileDatabase
import com.example.namegame.database.repository.ProfileRepository
import com.example.namegame.database.service.ProfileDao
import com.example.namegame.view.GameFragment
import com.example.namegame.view.LearnFragment
import com.example.namegame.view.MainActivity
import com.example.namegame.view.viewmodel.ViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RoomModule::class])
interface AppComponent {
    fun inject(learnFragment: LearnFragment)
    fun inject(gameFragment: GameFragment)
    fun dao(): ProfileDao
    fun database(): ProfileDatabase
    fun repository(): ProfileRepository
    fun application(): Application
}