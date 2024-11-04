package com.example.freetogame.ui.main.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freetogame.data.local.Game
import com.example.freetogame.data.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * ViewModel to provide and get data to [GameDetailScreen]
 *
 * @author Rigoberto Torres on 04/11/2024.
 * @version 0.0.1
 * @since 0.0.1
 */
@HiltViewModel
class GamesDetailViewModel @Inject constructor(
    private val repository: GameRepository
) : ViewModel() {


    private val _selectedGame = MutableStateFlow(
        Game(
            id = -1,
            title = "Cargando...",
            thumbnail = "",
            shortDescription = "Datos en proceso de carga.",
            gameUrl = "",
            genre = "Desconocido",
            platform = "Desconocido",
            publisher = "Desconocido",
            developer = "Desconocido",
            releaseDate = null,
            freeToGameProfileUrl = ""
        )
    )
    val selectedGame: StateFlow<Game> get() = _selectedGame


    var title by mutableStateOf("")
    var shortDescription by mutableStateOf("")
    var genre by mutableStateOf("")
    var platform by mutableStateOf("")
    var publisher by mutableStateOf("")
    var developer by mutableStateOf("")
    var releaseDate by mutableStateOf("")


    fun getGameById(gameId: Int) {
        viewModelScope.launch {
            repository.getGameById(gameId).collect { game ->
                _selectedGame.value = game
                game.let {
                    title = it.title
                    shortDescription = it.shortDescription
                    genre = it.genre
                    platform = it.platform
                    publisher = it.publisher
                    developer = it.developer
                    releaseDate = it.releaseDate?.toString() ?: ""
                }
            }
        }
    }


    fun deleteGame() {
        _selectedGame.value.let { game ->
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    repository.deleteGameById(game.id)
                }
            }
        }
    }
}