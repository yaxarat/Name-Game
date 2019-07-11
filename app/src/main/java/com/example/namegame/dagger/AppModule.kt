package com.example.namegame.dagger

import android.app.Application
import dagger.Module
import javax.inject.Singleton
import dagger.Provides


@Module
class AppModule(var app: Application) {

    @Provides
    @Singleton
    fun providesApplication(): Application {
        return app
    }
}