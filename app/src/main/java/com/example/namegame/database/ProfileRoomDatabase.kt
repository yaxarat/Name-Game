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

        fun getDatabase(context: Context): ProfileRoomDatabase? {
            if (INSTANCE == null){
                synchronized(ProfileRoomDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, ProfileRoomDatabase::class.java, "Profile_database").build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}