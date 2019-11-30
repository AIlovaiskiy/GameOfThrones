package ru.skillbranch.gameofthrones.di.modules

import dagger.Module
import dagger.Provides
import ru.skillbranch.gameofthrones.routers.CharacterScreenRouter
import ru.skillbranch.gameofthrones.routers.CharactersListScreenRouter
import ru.skillbranch.gameofthrones.routers.MainActivityRouter
import ru.skillbranch.gameofthrones.routers.SplashScreenRouter
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Singleton


@Module
class NavigationModule {

    @Singleton
    @Provides
    fun provideCicerone(): Cicerone<Router> =
        Cicerone.create()

    @Singleton
    @Provides
    fun provideCiceroneRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @Singleton
    @Provides
    fun provideCiceroneNavigatorHolder(
        cicerone: Cicerone<Router>
    ): NavigatorHolder = cicerone.navigatorHolder

    @Provides
    fun provideMainActivityRouter(router: Router): MainActivityRouter =
        MainActivityRouter(router)

    @Provides
    fun provideSplashScreenRouter(router: Router): SplashScreenRouter =
        SplashScreenRouter(router)

    @Provides
    fun provideCharacterScreenRouter(router: Router): CharacterScreenRouter =
        CharacterScreenRouter(router)

    @Provides
    fun provideCharactersListScreenRouter(router: Router): CharactersListScreenRouter =
        CharactersListScreenRouter(router)
}