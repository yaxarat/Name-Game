package com.example.namegame.di

import android.app.Application
import androidx.room.Room
import com.example.namegame.database.repository.ProfileDatabase
import com.example.namegame.database.repository.ProfileRepository
import com.example.namegame.database.service.ProfileDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(app: Application) {
    private val database = Room.databaseBuilder(app, ProfileDatabase::class.java, "profile.db").build()

    @Singleton
    @Provides
    fun providesDatabase(): ProfileDatabase {
        return database
    }

    @Singleton
    @Provides
    fun providesDao(): ProfileDao {
        return  database.profilesDao()
    }

    @Singleton
    @Provides
    fun providesRepository(dao: ProfileDao): ProfileRepository {
        return ProfileRepository(dao)
    }
}