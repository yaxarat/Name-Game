package com.example.namegame.data

data class Profile(
    var firstName: String,
    var headshot: Headshot,
    var id: String,
    var jobTitle: String,
    var lastName: String,
    var slug: String,
    // TODO: This part creates error with Moshi
    //var socialLinks: List<Any>,
    var type: String
)