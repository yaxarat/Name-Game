package com.example.namegame.database.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.namegame.database.entity.Profile
import com.example.namegame.database.service.ProfileDao

@Database(entities = [Profile::class], version = 1)
abstract class ProfileDatabase : RoomDatabase() {
    abstract fun profilesDao(): ProfileDao

//    companion object {
//        @Volatile private var instance: ProfileDatabase? = null
//        private val LOCK = Any()
//
//        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
//            instance ?: buildDatabase(context).also { instance = it }
//        }
//
//        private fun buildDatabase(context: Context) =
//            Room.databaseBuilder(context.applicationContext, ProfileDatabase::class.java, "profile.db").build()
//    }
}