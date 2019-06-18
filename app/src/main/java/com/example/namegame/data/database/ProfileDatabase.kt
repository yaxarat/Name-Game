package com.example.namegame.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.namegame.data.entity.Profile
import com.example.namegame.data.service.ProfileDao

@Database(entities = [Profile::class], version = 11)
abstract class ProfileDatabase : RoomDatabase() {
    abstract fun profilesDao(): ProfileDao

    companion object {
        @Volatile private var instance: ProfileDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, ProfileDatabase::class.java, "profile.db").fallbackToDestructiveMigration().build()
    }
}