package com.example.namegame.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.namegame.model.Profile

@Dao
interface ProfileDao {
    //TODO: Create random select function here.

    @Insert
    suspend fun insert(profiles: List<Profile>)

    @Update
    suspend fun update(profiles: List<Profile>)

    @Query("SELECT * FROM people_table;")
    fun getProfiles(): List<Profile>
}