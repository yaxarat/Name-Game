package com.example.namegame.di

import com.example.namegame.database.repository.ProfileRepository
import com.example.namegame.view.GameFragment
import com.example.namegame.view.LearnFragment
import com.example.namegame.view.MainActivity
import com.example.namegame.view.SettingFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RoomModule::class, DataSourceModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(learnFragment: LearnFragment)
    fun inject(gameFragment: GameFragment)
    fun inject(profileRepository: ProfileRepository)
    fun inject(settingFragment: SettingFragment)
}