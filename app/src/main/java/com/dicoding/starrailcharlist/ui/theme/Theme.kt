package com.dicoding.starrailcharlist.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.contentColorFor
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.ViewCompat
import androidx.palette.graphics.Palette
import com.dicoding.starrailcharlist.R


private val DarkColorScheme = darkColors(
    primary = GoldColor,
    primaryVariant = GreyColor,
    secondary = LightPurpleColor
)

private val LightColorScheme = lightColors(
    primary = GoldColor,
    primaryVariant = GreyColor,
    secondary = LightPurpleColor
)

@Composable
@Preview
fun StarrailListThemePreview() {
    val context = LocalContext.current
    val imageBitmap = ImageBitmap.imageResource(id = R.drawable.stars_bg)

  StarrailListTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = getDominantColor(imageBitmap),
            contentColor = contentColorFor(getDominantColor(imageBitmap))
        ) {
        }
    }
}


private fun getDominantColor(imageBitmap: ImageBitmap): Color {
    val androidBitmap = imageBitmap.asAndroidBitmap()
    return Palette.Builder(androidBitmap)
        .generate()
        .dominantSwatch
        ?.rgb
        ?.let { Color(it) }
        ?: Color.White
}


@Composable
fun StarrailListTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) DarkColorScheme else LightColorScheme
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colors.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        content = {
            // Load your image from resources or any other source
            val imageBitmap: ImageBitmap = ImageBitmap.imageResource(id = R.drawable.stars_bg)

            // Use Image composable to display the image
            Image(
                painter = BitmapPainter(imageBitmap),
                contentDescription = null, // Provide a content description if needed
                modifier = Modifier.fillMaxSize()
            )

            // Display your UI content on top of the image
            content()
        }
    )
}

