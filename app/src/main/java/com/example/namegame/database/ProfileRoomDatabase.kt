package com.example.namegame.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.namegame.model.Profile
import com.example.namegame.service.ProfileDao

@Database(entities = [Profile::class], version = 1)
abstract class ProfileRoomDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao

    companion object {
        @Volatile
        private var INSTANCE: ProfileRoomDatabase? = null

        fun getDatabase(context: Context): ProfileRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProfileRoomDatabase::class.java,
                    "Profile_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}