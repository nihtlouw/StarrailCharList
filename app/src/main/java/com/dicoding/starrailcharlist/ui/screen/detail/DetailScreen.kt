package com.dicoding.starrailcharlist.ui.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.starrailcharlist.R
import com.dicoding.starrailcharlist.di.Injection
import com.dicoding.starrailcharlist.model.Starrail
import com.dicoding.starrailcharlist.model.StarrailData
import com.dicoding.starrailcharlist.ui.ViewModelFactory
import com.dicoding.starrailcharlist.ui.common.UiState
import com.dicoding.starrailcharlist.ui.components.ErrorContent
import com.dicoding.starrailcharlist.ui.components.Loading
import com.dicoding.starrailcharlist.ui.components.StarrailDetailBanner
import com.dicoding.starrailcharlist.ui.components.StarrailDetailDescription
import com.dicoding.starrailcharlist.ui.theme.StarrailListTheme

@Composable
fun DetailScreen(
    starrailTitle: String,
    modifier: Modifier = Modifier,
    viewModel: DetailVM = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateBack: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                Loading()
                viewModel.getStarrailByTitle(starrailTitle)
            }
            is UiState.Success -> {
                uiState.data?.let { starrail ->
                    DetailContent(
                        starrail = starrail,
                        modifier = modifier,
                        navigateBack = navigateBack,
                        viewModel = viewModel
                    )
                }
            }
            is UiState.Error -> ErrorContent()
        }
    }
}


@Composable
fun DetailContent(
    starrail: Starrail,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    viewModel: DetailVM
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Box(modifier = modifier.fillMaxWidth()) {
            StarrailDetailBanner(
                title = starrail.character,
                photo = starrail.photoUrl,
                studio = starrail.type
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.icon_back),
                    modifier = Modifier.clickable { navigateBack() }
                )
                Icon(
                    imageVector = if (starrail.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = stringResource(
                        if (starrail.isFavorite) R.string.icon_favorite else R.string.icon_not_favorite
                    ),
                    modifier = Modifier
                        .clickable(onClick = { viewModel.toggleFavorite(starrail) })
                        .size(30.dp)
                )
            }
        }
        StarrailDetailDescription(
            starrail = starrail
        )

    }
}


@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    StarrailListTheme {
        DetailContent(
            starrail = StarrailData.starrail[0],
            navigateBack = {},
            viewModel = DetailVM(Injection.provideRepository()) // Create a new instance of DetailViewModel
        )
    }
}