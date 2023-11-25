package com.dicoding.starrailcharlist.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dicoding.starrailcharlist.R
import com.dicoding.starrailcharlist.model.StarrailData
import com.dicoding.starrailcharlist.ui.theme.StarrailListTheme

@Composable
fun StarrailListItem(
    title: String,
    photoUrl: String,
    score: String,
    genre: String,
    modifier: Modifier = Modifier,
) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .testTag("animeItem")
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Box(modifier = modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = photoUrl,
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    placeholder = painterResource(R.drawable.placeholder_image),
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .size(width = 100.dp, height = 150.dp)
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp)
                ) {
                    Text(
                        text = title,
                        fontSize = 20.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = score,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        repeat(5) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                tint = Color(0xFFFF9800),
                                contentDescription = null,
                                modifier = Modifier.size(15.dp)
                            )
                        }
                    }
                    Text(
                        text = genre,
                        fontSize = 15.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AnimeListItemPreview() {
    StarrailListTheme {
        StarrailListItem(
            title = StarrailData.starrail[0].character,
            photoUrl = StarrailData.starrail[0].photoUrl,
            score = StarrailData.starrail[0].rare,
            genre = StarrailData.starrail[0].path,
        )
    }
}
