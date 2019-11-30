package ru.skillbranch.gameofthrones.di.modules

import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import ru.skillbranch.gameofthrones.di.subcomponents.CharacterScreenSubcomponent
import ru.skillbranch.gameofthrones.di.subcomponents.CharactersListScreenSubcomponent
import ru.skillbranch.gameofthrones.di.subcomponents.SplashScreenSubcomponent
import ru.skillbranch.gameofthrones.ui.CharacterScreenFragment
import ru.skillbranch.gameofthrones.ui.CharactersListScreenFragment
import ru.skillbranch.gameofthrones.ui.SplashScreenFragment

@Module(
    subcomponents = [
        CharacterScreenSubcomponent::class,
        CharactersListScreenSubcomponent::class,
        SplashScreenSubcomponent::class
    ]
)
abstract class FragmentsModule {

    @Binds
    @IntoMap
    @ClassKey(CharacterScreenFragment::class)
    internal abstract fun bindCharacterScreenFragment(
        factory: CharacterScreenSubcomponent.Factory
    ): AndroidInjector.Factory<*>

    @Binds
    @IntoMap
    @ClassKey(CharactersListScreenFragment::class)
    internal abstract fun bindCharactersListScreenFragment(
        factory: CharactersListScreenSubcomponent.Factory
    ): AndroidInjector.Factory<*>

    @Binds
    @IntoMap
    @ClassKey(SplashScreenFragment::class)
    internal abstract fun bindSplashScreenFragment(
        factory: SplashScreenSubcomponent.Factory
    ): AndroidInjector.Factory<*>

}