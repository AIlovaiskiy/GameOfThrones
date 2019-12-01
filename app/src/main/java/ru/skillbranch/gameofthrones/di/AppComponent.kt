package ru.skillbranch.gameofthrones.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import ru.skillbranch.gameofthrones.GameOfThroneApplication
import ru.skillbranch.gameofthrones.di.modules.*
import ru.skillbranch.gameofthrones.repositories.RootRepository
import javax.inject.Singleton

@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        NavigationModule::class,
        ActivityModule::class,
        FragmentsModule::class,
        DataModule::class]
)
@Singleton
interface AppComponent {

    fun inject(application: GameOfThroneApplication)
    fun inject(repository: RootRepository)
}