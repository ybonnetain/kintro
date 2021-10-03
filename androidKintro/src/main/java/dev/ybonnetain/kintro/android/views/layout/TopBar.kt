package dev.ybonnetain.kintro.android.views.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import dev.ybonnetain.kintro.android.R
import dev.ybonnetain.kintro.android.styles.KintroTheme
import dev.ybonnetain.kintro.android.styles.typography

@Composable
fun TopBar() {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
        elevation = 0.dp,
        modifier = Modifier.heightIn(min = 150.dp, max = 150.dp), // TODO wrap content ?
        title = {
            Column() {
                Spacer(modifier = Modifier.height(20.dp))
                Row() {
                    Column() {
                        Text(
                            text = "Hello\nCompose + KMP",
                            style = typography.h1
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painterResource(id = R.drawable.compose),
                        contentDescription = null
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    KintroTheme(darkTheme = false) {
        TopBar()
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarDarkPreview() {
    KintroTheme(darkTheme = true) {
        TopBar()
    }
}
