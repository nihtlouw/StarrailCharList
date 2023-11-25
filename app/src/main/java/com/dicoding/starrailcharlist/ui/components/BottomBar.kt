package com.dicoding.starrailcharlist.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dicoding.starrailcharlist.R
import com.dicoding.starrailcharlist.ui.navigation.NavigationItem
import com.dicoding.starrailcharlist.ui.navigation.Screen
import com.dicoding.starrailcharlist.ui.theme.StarrailListTheme


@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItem = listOf(
            NavigationItem(
                character = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home,
                contentDescription = stringResource(R.string.home_page)
            ),
            NavigationItem(
                character = stringResource(R.string.menu_about),
                icon = Icons.Default.Info,
                screen = Screen.About,
                contentDescription = stringResource(R.string.about_page)
            ),
            NavigationItem(
                character = stringResource(R.string.menu_favorite), // Sesuaikan dengan string di strings.xml
                icon = Icons.Default.Star,
                screen = Screen.Favorite,
                contentDescription = stringResource(R.string.favorite_page) // Sesuaikan dengan string di strings.xml
            )
        )
        BottomNavigation {
            navigationItem.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.contentDescription
                        )
                    },
                    label = { Text(item.character) },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}
@Preview
@Composable
fun BottomBarPreview() {
    val navController = rememberNavController()
    StarrailListTheme {
        BottomBar(
            navController = navController,
            modifier = Modifier
        )
    }
}