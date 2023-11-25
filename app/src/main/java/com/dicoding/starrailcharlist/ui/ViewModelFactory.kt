package com.dicoding.starrailcharlist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.starrailcharlist.data.StarrailRepository
import com.dicoding.starrailcharlist.ui.screen.detail.DetailVM
import com.dicoding.starrailcharlist.ui.screen.favorite.FavoriteVM
import com.dicoding.starrailcharlist.ui.screen.home.HomeVM

class ViewModelFactory(private val repository: StarrailRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeVM::class.java) -> HomeVM(repository) as T
            modelClass.isAssignableFrom(DetailVM::class.java) -> DetailVM(repository) as T
            modelClass.isAssignableFrom(FavoriteVM::class.java) -> FavoriteVM(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}