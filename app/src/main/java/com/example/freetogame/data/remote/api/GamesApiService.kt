package com.example.freetogame.data.remote.api


import com.example.freetogame.data.local.Game
import retrofit2.http.GET

/**
 * @author Rigoberto Torres on 01/11/2024.
 * @version 0.0.1
 * @since 0.0.1
 */
interface GamesApiService {

    /**
     * Get List of games
     */
    @GET("games")
    suspend fun getGamesList(
    ): List<Game>


}