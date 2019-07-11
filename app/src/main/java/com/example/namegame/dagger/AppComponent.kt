package com.example.namegame.dagger

import com.example.namegame.view.GameFragment
import com.example.namegame.view.LearnFragment
import com.example.namegame.view.MainActivity
import com.example.namegame.view.SettingFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class, DataSourceModule::class, NetworkModule::class, ViewModelModule::class])
// TODO: inner interface to provide app instance. Builder
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(learnFragment: LearnFragment)
    fun inject(gameFragment: GameFragment)
    fun inject(settingFragment: SettingFragment)
}