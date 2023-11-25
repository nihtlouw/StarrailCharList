package com.dicoding.starrailcharlist.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dicoding.starrailcharlist.R
import com.dicoding.starrailcharlist.model.StarrailData
import com.dicoding.starrailcharlist.ui.theme.StarrailListTheme

@Composable
fun StarrailDetailBanner(
    title: String,
    photo: String,
    studio: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f)
                .wrapContentHeight()
                .align(Alignment.CenterHorizontally)
        ) {
            AsyncImage(
                model = photo,
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                placeholder = painterResource(R.drawable.placeholder_image),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Text(
                text = studio,
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StarrailDetailBannerPreview() {
    StarrailListTheme {
        StarrailDetailBanner(
            title = StarrailData.starrail[0].character,
            studio = StarrailData.starrail[0].type,
            photo = StarrailData.starrail[0].photoUrl
        )
    }
}