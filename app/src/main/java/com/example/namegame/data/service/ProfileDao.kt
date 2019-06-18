package com.example.namegame.data.service


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.namegame.data.entity.Profile

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(profile: Profile)

    // TODO: do selection filters here

//    @Query("SELECT * FROM profiles_table WHERE id = :id")
    @Query("SELECT * FROM profiles_table WHERE id IN (SELECT id FROM profiles_table ORDER BY RANDOM() LIMIT 6)")
    fun getFirstSixProfiles(): LiveData<List<Profile>>
}