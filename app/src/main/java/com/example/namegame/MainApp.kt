package com.example.namegame

import android.app.Application
import com.example.namegame.dagger.AppComponent
import com.example.namegame.dagger.AppModule
import com.example.namegame.dagger.DaggerAppComponent

class MainApp : Application() {
    lateinit var appComponent: AppComponent

    companion object {
        lateinit var application: MainApp
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}