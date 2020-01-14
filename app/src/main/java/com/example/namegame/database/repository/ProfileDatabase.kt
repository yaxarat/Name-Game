package com.example.namegame.database.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.namegame.database.entity.Profile
import com.example.namegame.database.repository.service.ProfileDao

@Database(entities = [Profile::class], version = 1)
abstract class ProfileDatabase : RoomDatabase() {
    abstract fun profilesDao(): ProfileDao
}