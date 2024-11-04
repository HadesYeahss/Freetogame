package com.example.freetogame.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Upsert
import androidx.sqlite.db.SupportSQLiteQuery
import kotlinx.coroutines.flow.Flow

/**
 * @author Rigoberto Torres on 02/11/2024.
 * @version 0.0.1
 * @since 0.0.1
 */
@Dao
interface GameDao {

    @Upsert
    fun updateGame(dataModel: Game)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(games: List<Game>)

    @Query("SELECT * FROM games")
    fun getAllRecords(): Flow<List<Game>>

    @Query("SELECT * FROM games WHERE id = :id")
    fun getGameById(id: Int): Flow<Game>

    @Query("UPDATE games SET isDeleted = 1 WHERE id = :id")
    fun deleteGameById(id: Int)

    @Query("SELECT * FROM games WHERE genre = :category")
    fun getGamesByCategory(category: String): Flow<List<Game>>

    @Query(
        "SELECT * FROM games WHERE " +
                "title LIKE '%' || :query || '%' " +
                "OR genre LIKE '%' || :query || '%'" +
                "OR shortDescription LIKE '%' || :query || '%'" +
                "OR publisher LIKE '%' || :query || '%'" +
                "OR platform LIKE '%' || :query || '%'"
    )
    fun getGamesBySearch(query: String): Flow<List<Game>>

    @Query("select DISTINCT genre from games")
    fun getGenres(): Flow<List<String>>

    @Query(
        """
        SELECT * FROM Games 
        WHERE (:category IS NULL OR genre = :category)
        AND isDeleted = 0
        AND (title LIKE '%' || :query || '%' 
        OR shortDescription LIKE '%' || :query || '%' 
        OR publisher LIKE '%' || :query || '%'
        OR platform LIKE '%' || :query || '%'
        OR genre LIKE '%' || :query || '%')
    """
    )
    fun getGamesByCategoryAndSearch(category: String?, query: String): Flow<List<Game>>

    @RawQuery(observedEntities = [Game::class])
    fun query(query: SupportSQLiteQuery): Flow<List<Game>>
}