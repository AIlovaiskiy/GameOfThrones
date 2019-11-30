package ru.skillbranch.gameofthrones.di.subcomponents

import dagger.Subcomponent
import dagger.android.AndroidInjector
import ru.skillbranch.gameofthrones.di.modules.CharacterScreenModule
import ru.skillbranch.gameofthrones.ui.CharacterScreenFragment

@Subcomponent(modules = [CharacterScreenModule::class])
interface CharacterScreenSubcomponent : AndroidInjector<CharacterScreenFragment> {

    @Subcomponent.Factory
    abstract class Factory : AndroidInjector.Factory<CharacterScreenFragment> {

        override fun create(instance: CharacterScreenFragment): AndroidInjector<CharacterScreenFragment> {
            return fragmentModule(CharacterScreenModule(instance))
        }

        abstract fun fragmentModule(module: CharacterScreenModule): AndroidInjector<CharacterScreenFragment>
    }
}