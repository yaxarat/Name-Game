package com.example.namegame.database.repository

import com.example.namegame.database.entity.Profile
import com.example.namegame.database.repository.service.ProfileApi
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileDataSource @Inject constructor(profileApi: ProfileApi) {
    var downloadedProfilesObservable: Single<List<Profile>> = profileApi.getProfiles()
}