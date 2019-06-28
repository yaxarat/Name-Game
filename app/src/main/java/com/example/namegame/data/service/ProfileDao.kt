package com.example.namegame.data.service


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.namegame.data.entity.Profile

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun upsert(profile: Profile)

    // TODO: move the business logic elsewhere - filter from all profiles below instead
    @Query("SELECT * FROM profiles_table WHERE id IN (SELECT id FROM profiles_table ORDER BY RANDOM() LIMIT 6)")
    fun getSixProfiles(): LiveData<List<Profile>>

    @Query("SELECT * FROM profiles_table")
    fun getAllProfiles(): LiveData<List<Profile>>
}