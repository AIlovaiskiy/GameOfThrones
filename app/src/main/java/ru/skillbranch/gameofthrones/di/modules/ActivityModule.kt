package ru.skillbranch.gameofthrones.di.modules

import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import ru.skillbranch.gameofthrones.MainActivity
import ru.skillbranch.gameofthrones.di.subcomponents.MainActivitySubcomponent

@Module(subcomponents = [MainActivitySubcomponent::class])
abstract class ActivityModule {

    @Binds
    @IntoMap
    @ClassKey(MainActivity::class)
    internal abstract fun bindMainActivit(
        factory: MainActivitySubcomponent.Factory
    ): AndroidInjector.Factory<*>
}