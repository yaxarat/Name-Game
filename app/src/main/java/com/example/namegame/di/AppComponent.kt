package com.example.namegame.di

import com.example.namegame.view.GameFragment
import com.example.namegame.view.LearnFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RoomModule::class])
interface AppComponent {
    fun inject(learnFragment: LearnFragment)
    fun inject(gameFragment: GameFragment)
}