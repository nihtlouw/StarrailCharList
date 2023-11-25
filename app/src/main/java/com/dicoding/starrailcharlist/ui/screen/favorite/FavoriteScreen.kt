package com.dicoding.starrailcharlist.ui.screen.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.dicoding.starrailcharlist.ui.components.StarrailListItem


@Composable
fun FavoriteScreen(
    navController: NavHostController,
    favoriteViewModel: FavoriteVM = viewModel(),
    navigateToDetail: (String) -> Unit
) {
    val favoriteState by favoriteViewModel.favoriteState.collectAsState()

    LazyColumn {
        items(favoriteState.starrails) { starrail ->
            // Make each item clickable and navigate to DetailScreen
            Box(
                modifier = Modifier
                    .clickable {
                        navigateToDetail(starrail.character)
                    }
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                // Use StarrailListItem for each favorite item
                StarrailListItem(
                    title = starrail.character,
                    photoUrl = starrail.photoUrl,
                    score = starrail.rare,
                    genre = starrail.path
                )
            }
        }
    }

    // You can call this function to refresh the list when needed
    LaunchedEffect(Unit) {
        favoriteViewModel.refreshFavoriteList()
    }
}



