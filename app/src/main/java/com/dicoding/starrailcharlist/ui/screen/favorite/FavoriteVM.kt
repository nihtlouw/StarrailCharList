package com.dicoding.starrailcharlist.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.starrailcharlist.data.StarrailRepository
import com.dicoding.starrailcharlist.model.Favorite
import com.dicoding.starrailcharlist.model.Starrail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteVM(private val repository: StarrailRepository) : ViewModel() {
    private val _favoriteState: MutableStateFlow<Favorite> = MutableStateFlow(Favorite(emptyList()))
    val favoriteState: StateFlow<Favorite>
        get() = _favoriteState

    // Default constructor
    constructor() : this(StarrailRepository.getInstance())

    fun refreshFavoriteList() {
        viewModelScope.launch {
            // Fetch the current favorite list from the repository
            val favorites = repository.getFavorites()
            // Update the StateFlow
            _favoriteState.value = Favorite(favorites)
        }
    }

    fun markAsFavorite(starrail: Starrail) {
        viewModelScope.launch {
            repository.updateFavoriteStatus(starrail, true)
            refreshFavoriteList()
        }
    }

    fun unmarkAsFavorite(starrail: Starrail) {
        viewModelScope.launch {
            repository.updateFavoriteStatus(starrail, false)
            refreshFavoriteList()
        }
    }
}
