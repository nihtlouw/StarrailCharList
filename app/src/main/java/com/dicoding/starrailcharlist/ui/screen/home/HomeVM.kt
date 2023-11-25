package com.dicoding.starrailcharlist.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.starrailcharlist.data.StarrailRepository
import com.dicoding.starrailcharlist.model.Starrail
import com.dicoding.starrailcharlist.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeVM(private val repository: StarrailRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Starrail>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Starrail>>>
        get() = _uiState

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    private val _starrailList = mutableStateOf<List<Starrail>>(emptyList())
    val starrailList: State<List<Starrail>> get() = _starrailList

    fun getAllStarrail(newQuery: String) {
        _query.value = newQuery
        try {
            val filteredStarrail = repository.getStarrail().filter {
                it.character.contains(_query.value, ignoreCase = true)
            }
            _starrailList.value = filteredStarrail
            _uiState.value = UiState.Success(filteredStarrail)
        } catch (e: Exception) {
            _uiState.value = UiState.Error(e.message.toString())
        }
    }

    fun markAsFavorite(starrail: Starrail) {
        viewModelScope.launch {
            try {
                repository.markAsFavorite(starrail)
                getAllStarrail(_query.value)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun unmarkAsFavorite(starrail: Starrail) {
        viewModelScope.launch {
            try {
                repository.unmarkAsFavorite(starrail)
                getAllStarrail(_query.value)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message.toString())
            }
        }
    }
}
