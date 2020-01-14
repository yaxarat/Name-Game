package com.example.namegame.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.namegame.database.repository.ProfileRepository
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

    @Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
    @MapKey
    annotation class ViewModelKey(val value: KClass<out ViewModel>)

    @Provides
    @IntoMap
    @ViewModelKey(LearnViewModel::class)
    fun provideLearnViewModel(repository: ProfileRepository): ViewModel {
        return LearnViewModel(repository)
    }

    @Provides
    @IntoMap
    @ViewModelKey(GameViewModel::class)
    fun provideGameViewModel(repository: ProfileRepository): ViewModel {
        return GameViewModel(repository)
    }

    @Provides
    fun provideViewModelFactory(providerMap: Map<Class<out ViewModel>, Provider<ViewModel>>): ViewModelProvider.NewInstanceFactory {
        return ViewModelFactory(providerMap)
    }
}