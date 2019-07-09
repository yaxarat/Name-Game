package com.example.namegame.di

import com.example.namegame.database.repository.ProfileDataSourceImp
import com.example.namegame.database.repository.service.ProfileDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataSourceModule {

    @Provides
    @Singleton
    fun providesDataSource(): ProfileDataSource {
        return ProfileDataSourceImp()
    }
}