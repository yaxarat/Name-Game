package com.example.namegame.database.repository.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.namegame.database.entity.Profile
import io.reactivex.Single

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(profile: Profile)

    @Query("SELECT * FROM profiles_table WHERE id IN (SELECT id FROM profiles_table ORDER BY RANDOM() LIMIT 6)")
    fun getSixProfiles(): Single<List<Profile>>

    @Query("SELECT * FROM profiles_table")
    fun getAllProfiles(): Single<List<Profile>>
}