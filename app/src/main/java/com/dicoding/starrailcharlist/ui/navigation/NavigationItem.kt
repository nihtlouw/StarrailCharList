package com.dicoding.starrailcharlist.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val character: String,
    val icon: ImageVector,
    val screen: Screen,
    val contentDescription: String
)
