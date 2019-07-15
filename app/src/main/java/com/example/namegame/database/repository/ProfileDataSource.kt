package com.example.namegame.database.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.namegame.database.entity.Profile
import com.example.namegame.database.repository.service.ProfileApi
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileDataSource @Inject constructor(private val profileApi: ProfileApi) {
    private val liveProfiles = MutableLiveData<List<Profile>>()
    val downloadedProfiles: LiveData<List<Profile>> get() = liveProfiles

    fun fetchProfiles() {
        val disposable = profileApi.getProfiles()
           .subscribeOn(Schedulers.io())
           .subscribe {liveProfiles.postValue(it)}

        CompositeDisposable().add(disposable)
    }
}