package com.dicoding.starrailcharlist.model

data class Starrail(
    val id: String,
    val character: String,
    val photoUrl: String,
    val rare: String,
    val path: String,
    val type: String,
    val drawableResId: Int,
    val synopsis: String,
    var isFavorite: Boolean = false
)
data class Favorite(val starrails: List<Starrail>)

