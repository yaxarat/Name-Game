package com.example.namegame.database.repository.service

import androidx.lifecycle.LiveData
import com.example.namegame.database.entity.Profile

interface ProfileDataSource_Imp {
    val downloadedProfiles: LiveData<List<Profile>>


}