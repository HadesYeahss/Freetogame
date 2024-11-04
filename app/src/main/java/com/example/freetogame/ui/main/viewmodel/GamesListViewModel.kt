package com.example.freetogame.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freetogame.data.local.Game
import com.example.freetogame.data.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * ViewModel to provide and get data to [GameListScreen]
 *
 * @author Rigoberto Torres on 03/11/2024.
 * @version 0.0.1
 * @since 0.0.1
 */
@HiltViewModel
class GamesListViewModel @Inject constructor(
    val repository: GameRepository
) : ViewModel() {


    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory: StateFlow<String?> get() = _selectedCategory

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> get() = _searchQuery

    private val _categoryList =
        repository.getGenres().map { categories -> listOf("All") + categories }
    val categoryList: StateFlow<List<String>> = _categoryList.stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    val filteredGames: StateFlow<List<Game>> = combine(
        _selectedCategory,
        _searchQuery
    ) { category, query ->
        repository.getGamesByCategoryAndSearch(category, query)
    }.flatMapLatest { it }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())


    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    fun onCategorySelected(category: String?) {
        _selectedCategory.value = category
    }

}