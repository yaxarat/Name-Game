package com.example.namegame.di

import android.app.Application
import android.content.res.Resources
import com.example.namegame.data.database.ProfileDatabase
import com.example.namegame.data.repository.ProfileRepository
import com.example.namegame.data.repository.ProfileRepositoryImpl
import com.example.namegame.data.service.*
import com.example.namegame.viewmodel.GameViewModelFactory
import com.example.namegame.viewmodel.LearnViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MainApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MainApplication))

        bind() from  singleton { ProfileDatabase(instance()) }
        bind() from singleton { instance<ProfileDatabase>().profilesDao()}
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { ProfileApi(instance()) }
        bind<ProfileDataSource>() with singleton { ProfileDataSourceImpl(instance()) }
        bind<ProfileRepository>() with singleton { ProfileRepositoryImpl(instance(), instance()) }
        bind() from provider { GameViewModelFactory(instance()) }
        bind() from provider { LearnViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}