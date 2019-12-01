package ru.skillbranch.gameofthrones.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.skillbranch.gameofthrones.AppConfig
import ru.skillbranch.gameofthrones.data.Database
import ru.skillbranch.gameofthrones.data.storage.LocalStorage
import ru.skillbranch.gameofthrones.data.storage.RemoteStorage
import ru.skillbranch.gameofthrones.repositories.GameOfThroneRepository
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
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideRemoteStorage(retrofit: Retrofit): RemoteStorage {
        return retrofit.create(RemoteStorage::class.java)
    }

    @Provides
    fun provideRepository(
        localStorage: LocalStorage,
        remoteStorage: RemoteStorage
    ): GameOfThroneRepository {
        return GameOfThroneRepository(remoteStorage, localStorage)
    }

    @Singleton
    @Provides
    fun provideLocalStorageStorage(context: Context): LocalStorage {
        return LocalStorage(
            Room.databaseBuilder(
                context,
                Database::class.java,
                "game_of_throne"
            ).build()
        )
    }
}