package com.example.freetogame.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton
import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.freetogame.data.local.AppDatabase
import com.example.freetogame.data.local.GameDao
import com.example.freetogame.data.remote.api.GamesApiService
import com.example.freetogame.data.repository.GameRepository
import java.util.concurrent.Executors

/**
 * RepositoryModule to inject sources in app
 *
 * @author Rigoberto Torres on 01/11/2024.
 * @version 0.0.1
 * @since 0.0.1
 */
@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app, AppDatabase::class.java,
        "game_database"
    ).setQueryCallback({ sqlQuery, bindArgs ->
        Log.d("RoomQuery", "Query: $sqlQuery SQL Args: $bindArgs")
    }, Executors.newSingleThreadExecutor()).build()

    @Singleton
    @Provides
    fun provideGameDao(db: AppDatabase) = db.gameDao()


    @Singleton
    @Provides
    fun provideGameRepository(
        gameApiService: GamesApiService,
        gameDao: GameDao
    ): GameRepository {
        return GameRepository(gameApiService, gameDao)
    }

}