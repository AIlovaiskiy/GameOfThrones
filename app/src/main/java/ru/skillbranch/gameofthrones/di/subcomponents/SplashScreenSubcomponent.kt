package ru.skillbranch.gameofthrones.di.subcomponents

import dagger.Subcomponent
import dagger.android.AndroidInjector
import ru.skillbranch.gameofthrones.di.modules.SplashScreenModule
import ru.skillbranch.gameofthrones.ui.SplashScreenFragment


@Subcomponent(modules = [SplashScreenModule::class])
interface SplashScreenSubcomponent : AndroidInjector<SplashScreenFragment> {

    @Subcomponent.Factory
    abstract class Factory : AndroidInjector.Factory<SplashScreenFragment> {

        override fun create(instance: SplashScreenFragment): AndroidInjector<SplashScreenFragment> {
            return fragmentModule(SplashScreenModule(instance))
        }

        abstract fun fragmentModule(module: SplashScreenModule): AndroidInjector<SplashScreenFragment>
    }
}