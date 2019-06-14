package com.example.namegame.model
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "people_table")
data class Profile(
    @PrimaryKey
    var id: String,
    var firstName: String,
    var lastName: String,
    @Ignore
    var jobTitle: String,
    @Ignore
    var slug: String,
    @Ignore
    var type: String,
    var headshot: Headshot
    // TODO: This part creates error with Moshi
    //var socialLinks: List<Any>
)

