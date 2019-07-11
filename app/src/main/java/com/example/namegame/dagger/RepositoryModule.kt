package com.example.namegame.dagger

import android.app.Application
import androidx.room.Room
import com.example.namegame.database.repository.ProfileDatabase
import com.example.namegame.database.repository.service.ProfileDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun providesDatabase(app: Application): ProfileDatabase {
        return Room.databaseBuilder(app, ProfileDatabase::class.java, "profile.db").build()
    }

    @Singleton
    @Provides
    fun providesDao(database: ProfileDatabase): ProfileDao {
        return  database.profilesDao()
    }
}