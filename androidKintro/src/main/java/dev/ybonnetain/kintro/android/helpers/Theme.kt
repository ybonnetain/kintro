package dev.ybonnetain.kintro.android.helpers

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.ybonnetain.kintro.android.R

// Typography
//

private val QuickSand = FontFamily(
    Font(R.font.quicksand_regular),
    Font(R.font.quicksand_bold, FontWeight.Bold)
)

val typography = Typography(
    h1 = TextStyle(
        fontFamily = QuickSand,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    body1 = TextStyle(
        fontFamily = QuickSand,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)

// Colors
//

object ColorPalette {
    val layout = Color(0xfff2f2f7)
    val orange = Color(0xffffcc00)
    val peach = Color(0xFFFFE5B4)
    val yellow = Color(0xFFFFFF00)
    val darkOrange = Color(0xFFFF9501)
}

private val DarkColorPalette = darkColors(
    primary = ColorPalette.orange,
    primaryVariant = ColorPalette.peach,
    secondary = ColorPalette.yellow
)

private val LightColorPalette = lightColors(
    primary = ColorPalette.orange,
    primaryVariant = ColorPalette.peach,
    secondary = ColorPalette.yellow
)

// Application Theme (from material base)
//

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
