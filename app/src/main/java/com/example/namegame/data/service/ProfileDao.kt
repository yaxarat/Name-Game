package com.example.namegame.data.service


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.namegame.data.entity.Profile

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun upsert(profile: Profile)

    @Query("SELECT * FROM profiles_table LIMIT 6")
    fun getFirstSixProfiles(): LiveData<List<Profile>>
}