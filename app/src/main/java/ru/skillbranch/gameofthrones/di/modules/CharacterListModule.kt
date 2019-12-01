package ru.skillbranch.gameofthrones.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import ru.skillbranch.gameofthrones.ValueTransmitter
import ru.skillbranch.gameofthrones.repositories.GameOfThroneRepository
import ru.skillbranch.gameofthrones.routers.CharacterListRouter
import ru.skillbranch.gameofthrones.ui.CharacterListFragment
import ru.skillbranch.gameofthrones.ui.CharacterListViewModel
import ru.skillbranch.gameofthrones.ui.HOUSE_NAME

@Module
class CharacterListModule(private val fragment: CharacterListFragment) {

    @Provides
    fun provideViewModel(
        router: CharacterListRouter,
        repository: GameOfThroneRepository,
        valueTransmitter: ValueTransmitter
    ): CharacterListViewModel {
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                CharacterListViewModel(
                    router,
                    repository,
                    valueTransmitter,
                    fragment.arguments?.getString(HOUSE_NAME) ?: ""
                ) as T
        }
        return ViewModelProviders.of(
            fragment,
            factory
        ).get(CharacterListViewModel::class.java)
    }
}