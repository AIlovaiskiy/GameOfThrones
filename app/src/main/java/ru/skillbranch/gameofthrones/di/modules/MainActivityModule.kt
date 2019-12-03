package ru.skillbranch.gameofthrones.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import ru.skillbranch.gameofthrones.MainActivity
import ru.skillbranch.gameofthrones.MainActivityViewModel
import ru.skillbranch.gameofthrones.routers.MainActivityRouter

@Module
class MainActivityModule(private val activity: MainActivity) {

    @Provides
    internal fun provideViewModel(
        router: MainActivityRouter
    ): MainActivityViewModel {
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                MainActivityViewModel(router) as T
        }
        return ViewModelProviders.of(
            activity,
            factory
        ).get(MainActivityViewModel::class.java)
    }
}