package com.example.namegame.di

import com.example.namegame.database.repository.ProfileDataSourceImp
import com.example.namegame.database.repository.service.ProfileDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun providesDataSource(profileDataSourceImp: ProfileDataSourceImp): ProfileDataSource
}