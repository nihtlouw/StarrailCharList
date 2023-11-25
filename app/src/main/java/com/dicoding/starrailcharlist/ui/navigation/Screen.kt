package com.dicoding.starrailcharlist.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object About : Screen("about")
    object DetailStarrail : Screen("home/{starrailTitle}") {
        private fun encodeForDeepLink(string: String): String {
            return string.replace("/", "%2F")
                .replace("?", "%3F")
        }

        fun createRoute(starrailTitle: String) = "home/${encodeForDeepLink(starrailTitle)}"
    }

    object Favorite : Screen("favorite")
}

