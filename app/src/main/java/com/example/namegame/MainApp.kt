package com.example.namegame

import android.app.Application
import com.example.namegame.dagger.AppComponent
import com.example.namegame.dagger.AppModule
import com.example.namegame.dagger.DaggerAppComponent
import com.jakewharton.threetenabp.AndroidThreeTen

class MainApp : Application() {
    lateinit var appComponent: AppComponent

    companion object {
        lateinit var app: MainApp
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        AndroidThreeTen.init(this)
    }
}