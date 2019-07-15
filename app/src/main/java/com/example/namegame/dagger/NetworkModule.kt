package com.example.namegame.dagger

import com.example.namegame.database.repository.service.ProfileApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    private val willowtreeApiBaseUrl = "https://willowtreeapps.com/api/v1.0/"

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return  OkHttpClient.Builder().build()
    }

    @Singleton
    @Provides
    fun provideMoshi(): MoshiConverterFactory {
        return MoshiConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRxJava(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()

    }
    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient, converterFactory: MoshiConverterFactory, adapterFactory: RxJava2CallAdapterFactory): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(willowtreeApiBaseUrl)
            .addCallAdapterFactory(adapterFactory)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideProfileApi(retrofit: Retrofit): ProfileApi {
        return retrofit.create(ProfileApi::class.java)
    }
}