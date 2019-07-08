package com.example.namegame.di

import com.example.namegame.data.service.ProfileDao
import com.example.namegame.data.service.ProfileDao_Impl

import dagger.Binds
import dagger.Module

@Module
abstract class ServiceModule {
    @Binds
    abstract fun bindDao(dao: ProfileDao_Impl): ProfileDao
}
