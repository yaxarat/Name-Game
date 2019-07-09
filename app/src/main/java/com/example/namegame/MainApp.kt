package com.example.namegame

import android.app.Application
import com.example.namegame.di.AppComponent
import com.example.namegame.di.AppModule
import com.example.namegame.di.DaggerAppComponent
import com.example.namegame.di.RoomModule
import com.jakewharton.threetenabp.AndroidThreeTen

class MainApp: Application() {
    lateinit var appComponent: AppComponent

    companion object {
        lateinit var app: MainApp
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).roomModule(RoomModule(this)).build()
        AndroidThreeTen.init(this)
    }
}