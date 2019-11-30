package ru.skillbranch.gameofthrones.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import ru.skillbranch.gameofthrones.repositories.RootRepository
import ru.skillbranch.gameofthrones.routers.CharactersListScreenRouter
import ru.skillbranch.gameofthrones.ui.CharactersListScreenFragment
import ru.skillbranch.gameofthrones.ui.CharactersListScreenViewModel

@Module
class CharactersListScreenModule(private val fragment: CharactersListScreenFragment) {

    @Provides
    fun provideViewModel(
        router: CharactersListScreenRouter,
        repository: RootRepository
    ): CharactersListScreenViewModel {
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                CharactersListScreenViewModel(router, repository) as T
        }
        return ViewModelProviders.of(
            fragment,
            factory
        ).get(CharactersListScreenViewModel::class.java)
    }
}