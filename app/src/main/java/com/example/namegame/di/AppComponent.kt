package com.example.namegame.di

import com.example.namegame.database.repository.ProfileDataSourceImp
import com.example.namegame.view.GameFragment
import com.example.namegame.view.LearnFragment
import com.example.namegame.view.MainActivity
import com.example.namegame.view.SettingFragment
import com.example.namegame.view.viewmodel.GameViewModel
import com.example.namegame.view.viewmodel.LearnViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class, DataSourceModule::class, NetworkModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(learnFragment: LearnFragment)
    fun inject(gameFragment: GameFragment)
    fun inject(profileDataSourceImp: ProfileDataSourceImp)
    fun inject(settingFragment: SettingFragment)
}