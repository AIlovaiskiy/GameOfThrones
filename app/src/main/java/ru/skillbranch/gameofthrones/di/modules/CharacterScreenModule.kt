package ru.skillbranch.gameofthrones.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import ru.skillbranch.gameofthrones.repositories.RootRepository
import ru.skillbranch.gameofthrones.routers.CharacterScreenRouter
import ru.skillbranch.gameofthrones.ui.CharacterScreenFragment
import ru.skillbranch.gameofthrones.ui.CharacterScreenViewModel

@Module
class CharacterScreenModule(private val fragment: CharacterScreenFragment) {

    @Provides
    fun provideViewModel(
        router: CharacterScreenRouter,
        repository: RootRepository
    ): CharacterScreenViewModel {
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                CharacterScreenViewModel(router, repository) as T
        }
        return ViewModelProviders.of(
            fragment,
            factory
        ).get(CharacterScreenViewModel::class.java)
    }
}