package ru.skillbranch.gameofthrones.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.skillbranch.gameofthrones.ValueTransmitter
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context =
        context

    @Provides
    @Singleton
    fun provideTransmitter(): ValueTransmitter =
        ValueTransmitter()
}