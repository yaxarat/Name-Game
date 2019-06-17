package com.example.namegame.model
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "people_table")
data class Profile(
    @PrimaryKey
    var id: String,
    var firstName: String,
    var lastName: String
//    var headshot: Headshot
)

