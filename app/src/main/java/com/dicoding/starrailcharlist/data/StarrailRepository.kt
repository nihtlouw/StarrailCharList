package com.dicoding.starrailcharlist.data

import com.dicoding.starrailcharlist.model.Starrail
import com.dicoding.starrailcharlist.model.StarrailData

class StarrailRepository {
    private val starrailList = mutableListOf<Starrail>()

    init {
        if (starrailList.isEmpty()) {
            StarrailData.starrail.forEach {
                starrailList.add(it)
            }
        }
    }

    fun getStarrail(): List<Starrail> {
        return StarrailData.starrail
    }

    fun getStarrailByTitle(title: String): Starrail {
        return starrailList.first {
            it.character == title
        }
    }

    fun markAsFavorite(starrail: Starrail) {
        val updatedStarrail = starrail.copy(isFavorite = true)
        updateStarrailList(updatedStarrail)
    }

    fun unmarkAsFavorite(starrail: Starrail) {
        val updatedStarrail = starrail.copy(isFavorite = false)
        updateStarrailList(updatedStarrail)
    }

    fun getFavorites(): List<Starrail> {
        return starrailList.filter { it.isFavorite }
    }

    private fun updateStarrailList(updatedStarrail: Starrail) {
        val index = starrailList.indexOfFirst { it.character == updatedStarrail.character }
        if (index != -1) {
            starrailList[index] = updatedStarrail
        }
    }

    fun updateFavoriteStatus(starrail: Starrail, isFavorite: Boolean) {
        val updatedStarrail = starrail.copy(isFavorite = isFavorite)
        updateStarrailList(updatedStarrail)
    }

    companion object {
        @Volatile
        private var instance: StarrailRepository? = null

        fun getInstance(): StarrailRepository =
            instance ?: synchronized(this) {
                StarrailRepository().apply {
                    instance = this
                }
            }
    }

}