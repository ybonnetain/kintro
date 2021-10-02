package dev.ybonnetain.kintro.android.styles

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = Yellow300,
    primaryVariant = Yellow500,
    onPrimary = Color.Black,
    secondary = Yellow400,
    secondaryVariant = Yellow300,
    onSecondary = Color.White,
    error = Red500,
    onError = Color.White,
    background = Gray100,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black
)

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    primary = Color.Black,
    primaryVariant = Color.Black,
    onPrimary = Color.White,
    secondary = Gray300,
    secondaryVariant = Gray300,
    onSecondary = Gray400,
    error = Red500,
    onError = Color.White,
    background = Color.Black,
    onBackground = Color.White,
    surface = Gray300,
    onSurface = Color.White
)

@Composable
fun KintroTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        content = content,
        typography = typography
    )
}
