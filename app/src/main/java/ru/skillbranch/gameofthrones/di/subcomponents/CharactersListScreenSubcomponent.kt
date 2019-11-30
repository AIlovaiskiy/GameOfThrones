package ru.skillbranch.gameofthrones.di.subcomponents

import dagger.Subcomponent
import dagger.android.AndroidInjector
import ru.skillbranch.gameofthrones.di.modules.CharactersListScreenModule
import ru.skillbranch.gameofthrones.ui.CharactersListScreenFragment


@Subcomponent(modules = [CharactersListScreenModule::class])
interface CharactersListScreenSubcomponent : AndroidInjector<CharactersListScreenFragment> {

    @Subcomponent.Factory
    abstract class Factory : AndroidInjector.Factory<CharactersListScreenFragment> {

        override fun create(instance: CharactersListScreenFragment): AndroidInjector<CharactersListScreenFragment> {
            return fragmentModule(CharactersListScreenModule(instance))
        }

        abstract fun fragmentModule(module: CharactersListScreenModule): AndroidInjector<CharactersListScreenFragment>
    }
}