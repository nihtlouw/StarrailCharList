package com.dicoding.starrailcharlist.ui.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.dicoding.starrailcharlist.R
import com.dicoding.starrailcharlist.ui.theme.StarrailListTheme

@Composable
fun TopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = stringResource(id = R.string.app_name))
        }
    )
}

@Preview
@Composable
fun TopBarPreview() {
    StarrailListTheme {
        TopBar()
    }
}
