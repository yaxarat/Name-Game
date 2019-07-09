package com.example.namegame.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profiles_table")
data class Profile(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val firstName: String,
    val lastName: String,
    val jobTitle: String,
    @Embedded(prefix = "headshot_")
    val headshot: Headshot
)