package com.example.namegame.app

import android.app.Application
import com.example.namegame.di.ViewModelFactoryComponent
import com.example.namegame.view.viewmodel.ViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen

class Application: Application() {
    private val ViewModelFactoryComponent = null

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}