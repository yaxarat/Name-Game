package com.example.namegame.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profiles_table")
data class Profile(
    var firstName: String,
    @Embedded(prefix = "headshot_")
    var headshot: Headshot,
    @PrimaryKey(autoGenerate = false)
    var id: String,
    var lastName: String
)