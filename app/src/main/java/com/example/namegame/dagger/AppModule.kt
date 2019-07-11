package com.example.namegame.dagger

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class AppModule(val application: Application) {

    @Provides
    fun providesApplication(): Application {
        return application
    }
}