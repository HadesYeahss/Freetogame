package com.example.freetogame.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * @author Rigoberto Torres on 02/11/2024.
 * @version 0.0.1
 * @since 0.0.1
 */
@Database(entities = [Game::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}