package com.dicoding.starrailcharlist.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.starrailcharlist.di.Injection
import com.dicoding.starrailcharlist.model.Starrail
import com.dicoding.starrailcharlist.ui.ViewModelFactory
import com.dicoding.starrailcharlist.ui.common.UiState
import com.dicoding.starrailcharlist.ui.components.ErrorContent
import com.dicoding.starrailcharlist.ui.components.Loading
import com.dicoding.starrailcharlist.ui.components.SearchBar
import com.dicoding.starrailcharlist.ui.components.StarrailListItem
import com.dicoding.starrailcharlist.ui.theme.StarrailListTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeVM = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (String) -> Unit
) {
    val query by viewModel.query

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                Loading()
                viewModel.getAllStarrail(query)
            }
            is UiState.Success -> {
                HomeContent(
                    starrailList = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                    query = query,
                    onQueryChange = viewModel::getAllStarrail
                )
            }
            is UiState.Error -> ErrorContent()
        }
    }
}

@Composable
fun HomeContent(
    starrailList: List<Starrail>,
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    navigateToDetail: (String) -> Unit
) {
    Column(modifier = modifier) {
        SearchBar(
            query = query,
            onQueryChange = onQueryChange
        )
        Box(modifier = modifier) {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = modifier.testTag("starrailList")
            ) {
                items(starrailList) { data ->
                    StarrailListItem(
                        title = data.character,
                        photoUrl = data.photoUrl,
                        score = data.rare,
                        genre = data.path,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navigateToDetail(data.character)
                            },
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    StarrailListTheme {
        HomeScreen(navigateToDetail = {})
    }
}