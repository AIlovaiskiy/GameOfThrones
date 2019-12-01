package ru.skillbranch.gameofthrones.di.subcomponents

import dagger.Subcomponent
import dagger.android.AndroidInjector
import ru.skillbranch.gameofthrones.di.modules.CharacterListModule
import ru.skillbranch.gameofthrones.ui.CharacterListFragment


@Subcomponent(modules = [CharacterListModule::class])
interface CharacterListSubcomponent : AndroidInjector<CharacterListFragment> {

    @Subcomponent.Factory
    abstract class Factory : AndroidInjector.Factory<CharacterListFragment> {

        override fun create(instance: CharacterListFragment): AndroidInjector<CharacterListFragment> {
            return fragmentModule(CharacterListModule(instance))
        }

        abstract fun fragmentModule(module: CharacterListModule): AndroidInjector<CharacterListFragment>
    }
}