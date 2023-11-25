package com.dicoding.starrailcharlist

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dicoding.starrailcharlist.ui.components.BottomBar
import com.dicoding.starrailcharlist.ui.components.TopBar
import com.dicoding.starrailcharlist.ui.navigation.Screen
import com.dicoding.starrailcharlist.ui.screen.about.AboutScreen
import com.dicoding.starrailcharlist.ui.screen.detail.DetailScreen
import com.dicoding.starrailcharlist.ui.screen.favorite.FavoriteScreen
import com.dicoding.starrailcharlist.ui.screen.home.HomeScreen
import com.dicoding.starrailcharlist.ui.theme.StarrailListTheme

@Composable
fun StarrailListApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentState = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = { TopBar() },
        bottomBar = {
            if (currentState != Screen.DetailStarrail.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { starrailTitle ->
                        navController.navigate(Screen.DetailStarrail.createRoute(starrailTitle))
                    }
                )
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen(
                    navController = navController,
                    navigateToDetail = { starrailTitle ->
                        navController.navigate(Screen.DetailStarrail.createRoute(starrailTitle))
                    }
                )
            }
            composable(Screen.About.route) {
                AboutScreen()
            }
            composable(
                route = Screen.DetailStarrail.route,
                arguments = listOf(navArgument("starrailTitle") { type = NavType.StringType })
            ) {
                val title = it.arguments?.getString("starrailTitle") ?: ""
                DetailScreen(
                    starrailTitle = title,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StarrailListAppPreview() {
    StarrailListTheme {
        StarrailListApp()
    }
}
