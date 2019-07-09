package com.example.namegame.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.namegame.model.Profile

@Dao
interface ProfileDao {
    @Insert
    fun insert(profile: Profile)

    @Update
    suspend fun update(profile: Profile)

    @Query("SELECT * FROM people_table;")
    fun getProfiles(): List<Profile>
}