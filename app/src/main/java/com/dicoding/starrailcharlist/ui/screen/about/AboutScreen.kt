package com.dicoding.starrailcharlist.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.starrailcharlist.R
import com.dicoding.starrailcharlist.ui.theme.StarrailListTheme

@Composable
fun AboutScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.hilmysnk), // Replace "your_image_name" with the actual name of your image resource
                contentDescription = stringResource(id = R.string.profile_picture),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.profile_name),
                style = MaterialTheme.typography.h6
            )
            Text(
                text = stringResource(id = R.string.profile_email),
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DriverItemPreview() {
    StarrailListTheme {
        AboutScreen()
    }
}