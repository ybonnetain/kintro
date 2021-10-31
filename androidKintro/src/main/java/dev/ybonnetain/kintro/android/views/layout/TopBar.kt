package dev.ybonnetain.kintro.android.views.layout

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import dev.ybonnetain.kintro.android.styles.KintroTheme
import dev.ybonnetain.kintro.android.styles.typography

@Composable
fun TopBar(title: String) {

    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
        elevation = 0.dp,
        modifier = Modifier
            .heightIn(min = 94.dp, max = 94.dp) // TODO wrap content ?
            .padding(horizontal = 0.dp, vertical = 24.dp),
        title = {
            Row() {
                Text(
                    text = title,
                    style = typography.h1
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    KintroTheme(darkTheme = false) {
        TopBar("Hello")
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarDarkPreview() {
    KintroTheme(darkTheme = true) {
        TopBar("Hello")
    }
}
