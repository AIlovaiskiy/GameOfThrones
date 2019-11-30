package ru.skillbranch.gameofthrones.di.subcomponents

import dagger.Subcomponent
import dagger.android.AndroidInjector
import ru.skillbranch.gameofthrones.MainActivity
import ru.skillbranch.gameofthrones.di.modules.MainActivityModule
import ru.skillbranch.gameofthrones.di.modules.NavigationModule

@Subcomponent(modules = [MainActivityModule::class])
interface MainActivitySubcomponent : AndroidInjector<MainActivity> {

    @Subcomponent.Factory
    abstract class Factory : AndroidInjector.Factory<MainActivity> {

        override fun create(instance: MainActivity): AndroidInjector<MainActivity> {
            return fragmentModule(MainActivityModule(instance))
        }

        abstract fun fragmentModule(module: MainActivityModule): AndroidInjector<MainActivity>
    }
}