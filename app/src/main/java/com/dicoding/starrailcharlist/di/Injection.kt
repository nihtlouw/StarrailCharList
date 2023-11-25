package com.dicoding.starrailcharlist.di

import com.dicoding.starrailcharlist.data.StarrailRepository

object Injection {
    fun provideRepository(): StarrailRepository {
        return StarrailRepository.getInstance()
    }
}
