package com.example.namegame.dagger

import com.example.namegame.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class, NetworkModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}