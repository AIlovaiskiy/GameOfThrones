package ru.skillbranch.gameofthrones

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import ru.skillbranch.gameofthrones.di.AppComponent
import ru.skillbranch.gameofthrones.di.DaggerAppComponent
import ru.skillbranch.gameofthrones.di.modules.AppModule
import javax.inject.Inject

class GameOfThroneApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
    var appComponent: AppComponent? = null

    companion object {
        lateinit var instance: GameOfThroneApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this.baseContext))
            .build()
        appComponent?.inject(this)
    }
}