package ru.skillbranch.gameofthrones.di.modules

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import ru.skillbranch.gameofthrones.AppConfig
import ru.skillbranch.gameofthrones.repositories.RootRepository
import ru.skillbranch.gameofthrones.storage.LocalStorage
import ru.skillbranch.gameofthrones.storage.RemoteStorage
import javax.inject.Singleton

@Module
class DataModule {

    @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN") // Иначе даггер не находит зависимость
    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofitV2(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConfig.BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideRemoteStorage(retrofit: Retrofit): RemoteStorage {
        return retrofit.create(RemoteStorage::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(): RootRepository {
        return RootRepository()
    }

    @Provides
    fun provideLocalStorageStorage(): LocalStorage {
        return LocalStorage
    }
}