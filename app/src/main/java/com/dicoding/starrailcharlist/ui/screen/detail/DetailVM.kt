package com.dicoding.starrailcharlist.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.starrailcharlist.data.StarrailRepository
import com.dicoding.starrailcharlist.model.Starrail
import com.dicoding.starrailcharlist.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailVM(private val repository: StarrailRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Starrail>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Starrail>>
        get() = _uiState

    fun getStarrailByTitle(animeTitle: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getStarrailByTitle(animeTitle))
        }
    }

    fun toggleFavorite(starrail: Starrail) {
        viewModelScope.launch {
            try {
                if (starrail.isFavorite) {
                    unmarkAsFavorite(starrail)
                } else {
                    markAsFavorite(starrail)
                }
                // Refresh the UI state after toggling favorite
                _uiState.value = UiState.Success(repository.getStarrailByTitle(starrail.character))
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun markAsFavorite(starrail: Starrail) {
        viewModelScope.launch {
            try {
                repository.markAsFavorite(starrail)
                // Refresh the UI state after marking as favorite
                _uiState.value = UiState.Success(repository.getStarrailByTitle(starrail.character))
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun unmarkAsFavorite(starrail: Starrail) {
        viewModelScope.launch {
            try {
                repository.unmarkAsFavorite(starrail)
                // Refresh the UI state after unmarking as favorite
                _uiState.value = UiState.Success(repository.getStarrailByTitle(starrail.character))
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message.toString())
            }
        }
    }
}