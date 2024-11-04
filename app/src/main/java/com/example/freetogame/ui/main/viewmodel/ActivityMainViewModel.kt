package com.example.freetogame.ui.main.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freetogame.data.remote.response.ResponseResult
import com.example.freetogame.data.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * ViewModel to provide and get data to [MainActivity]
 *
 * @author Rigoberto Torres on 03/11/2024.
 * @version 0.0.1
 * @since 0.0.1
 */
@HiltViewModel
class ActivityMainViewModel @Inject constructor(
    val repository: GameRepository,
    val sharedPreferences: SharedPreferences
) : ViewModel() {

    fun savePreference(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getPreference(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    val _isSplashDataLoaded = MutableStateFlow(false)
    val isSplashDataLoaded: StateFlow<Boolean> = _isSplashDataLoaded

    fun getAllGames() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.getAllGamesFromServer().collect { result ->
                    when (result) {
                        is ResponseResult.Success -> {
                            repository.saveAllGames(result.data)
                            _isSplashDataLoaded.value = true
                        }

                        is ResponseResult.Failure -> {
                            _isSplashDataLoaded.value = false
                        }
                    }
                }
            }
        }
    }
}