package com.example.namegame.di

import com.example.namegame.view.viewmodel.ViewModelFactory
import dagger.Component

@Component(modules = [ServiceModule::class])
interface ViewModelFactoryComponent {
    fun getViewModelFactory(): ViewModelFactory
}