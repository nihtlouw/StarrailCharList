package com.dicoding.starrailcharlist.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.starrailcharlist.model.Starrail
import com.dicoding.starrailcharlist.model.StarrailData
import com.dicoding.starrailcharlist.ui.theme.StarrailListTheme

@Composable
fun StarrailDetailDescription(
    starrail: Starrail,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = starrail.path,
                    style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .weight(1f) // Expand to take available space
                        .padding(start = 8.dp)
                )
                // Use drawableResId from Starrail object to get the drawable resource
                val drawableResId = starrail.drawableResId

                // Load the drawable resource using painterResource
                val context = LocalContext.current
                val painter = painterResource(id = context.resources.getIdentifier(drawableResId.toString(), "drawable", context.packageName))

                // Display the image with adjusted size for type "be"
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .size(25.dp), // Adjust the size as needed
                    contentScale = ContentScale.Crop
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = starrail.rare,
                    style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .weight(1f) // Expand to take available space
                        .padding(start = 8.dp)
                )
                (0 until 5).forEach {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        tint = Color(0xFFFF9800),
                        contentDescription = null,
                        modifier = Modifier
                            .size(25.dp)
                    )
                }
            }

            Text(
                text = buildAnnotatedString {
                    withStyle(style = MaterialTheme.typography.subtitle1.toSpanStyle().copy(fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)) {
                        append(starrail.synopsis)
                    }
                }
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun StarrailDetailDescriptionPreview() {
    StarrailListTheme {
        StarrailDetailDescription(starrail = StarrailData.starrail[0])
    }
}
