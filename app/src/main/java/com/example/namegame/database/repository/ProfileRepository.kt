package com.example.namegame.database.repository

import android.annotation.SuppressLint
import android.util.Log
import com.example.namegame.database.entity.Profile
import com.example.namegame.database.repository.service.ProfileDao
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@SuppressLint("CheckResult")
@Singleton
class ProfileRepository @Inject constructor(private val profileDao: ProfileDao, private  val profileDataSource: ProfileDataSource) {

    fun fetchProfiles() {
        profileDataSource.downloadedProfilesObservable
            .subscribeOn(Schedulers.io())
            .subscribe(
                {newFetchedProfiles -> saveFetchedProfiles(Single.just(newFetchedProfiles))},
                {Log.e("tag", "Could not fetch new profiles")}
            )
    }

    fun getProfiles(): Single<List<Profile>> {
        return profileDao.getSixProfiles()
    }

    fun getAllProfiles(): Single<List<Profile>> {
        return profileDao.getAllProfiles()
    }

    private fun saveFetchedProfiles(fetchedProfile: Single<List<Profile>>) {
        fetchedProfile
            .subscribeOn(Schedulers.io())
            .subscribe { profiles -> for (profile in profiles) { profileDao.upsert(profile) }}
    }
}