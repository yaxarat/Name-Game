package com.example.namegame.di

import androidx.lifecycle.ViewModel
import com.example.namegame.view.viewmodel.GameViewModel
import com.example.namegame.view.viewmodel.LearnViewModel
import com.example.namegame.view.viewmodel.ViewModelFactory
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider
import kotlin.reflect.KClass

@Module
class ViewModelModule {
    @Target(AnnotationTarget.FUNCTION)
    @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
    @MapKey
    annotation class ViewModelKey(val value: KClass<out ViewModel>)

    @Provides
    fun provideViewModelFactory(providerMap: Map<Class<out ViewModel>, Provider<ViewModel>>): ViewModelFactory {
        return ViewModelFactory(providerMap)
    }

    @Provides
    @IntoMap
    @ViewModelKey(LearnViewModel::class)
    fun provideLearnViewModel(): ViewModel {
        return LearnViewModel()
    }

    @Provides
    @IntoMap
    @ViewModelKey(GameViewModel::class)
    fun provideGameViewModel(): ViewModel {
        return GameViewModel()
    }
}