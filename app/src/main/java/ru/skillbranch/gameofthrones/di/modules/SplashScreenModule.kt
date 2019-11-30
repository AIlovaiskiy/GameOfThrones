package ru.skillbranch.gameofthrones.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import ru.skillbranch.gameofthrones.di.subcomponents.SplashScreenSubcomponent
import ru.skillbranch.gameofthrones.repositories.RootRepository
import ru.skillbranch.gameofthrones.routers.SplashScreenRouter
import ru.skillbranch.gameofthrones.ui.SplashScreenFragment
import ru.skillbranch.gameofthrones.ui.SplashScreenViewModel


@Module
class SplashScreenModule(private val fragment: SplashScreenFragment) {

    @Provides
    fun provideViewModel(
        router: SplashScreenRouter,
        repository: RootRepository
    ): SplashScreenViewModel {
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                SplashScreenViewModel(router, repository) as T
        }
        return ViewModelProviders.of(
            fragment,
            factory
        ).get(SplashScreenViewModel::class.java)
    }
}