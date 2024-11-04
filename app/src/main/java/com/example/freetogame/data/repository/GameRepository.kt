package com.example.freetogame.data.repository


import com.example.freetogame.data.local.Game
import com.example.freetogame.data.local.GameDao
import com.example.freetogame.data.remote.api.GamesApiService
import com.example.freetogame.data.remote.response.BaseApiResponse
import com.example.freetogame.data.remote.response.ResponseResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


/**
 * Repository to access api data
 *
 * @author Rigoberto Torres on 1/11/2024.
 * @version 0.0.1
 * @since 0.0.1
 */
@ActivityRetainedScoped
class GameRepository @Inject constructor(
    private val gamesApiService: GamesApiService,
    private val gameDao: GameDao
) : BaseApiResponse() {


    /**
     * Get List of games
     */
    fun getAllGamesFromServer(): Flow<ResponseResult<List<Game>>> {
        return flow {
            emitSafeApiCall(this) { gamesApiService.getGamesList() }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun saveAllGames(games: List<Game>) {
        gameDao.insertAll(games)
    }

    fun getGameById(gameId: Int): Flow<Game> {
        return gameDao.getGameById(gameId)
    }

    fun deleteGameById(gameId: Int) {
        return gameDao.deleteGameById(gameId)
    }

    fun updateGame(dataModel: Game) {
        return gameDao.updateGame(dataModel)
    }

    fun getGenres(): Flow<List<String>> {
        return gameDao.getGenres()
    }

    fun getGamesByCategoryAndSearch(category: String?, query: String): Flow<List<Game>> {
        return gameDao.getGamesByCategoryAndSearch(category, query)
    }



}


