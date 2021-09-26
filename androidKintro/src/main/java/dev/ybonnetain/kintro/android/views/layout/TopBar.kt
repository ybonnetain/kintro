package dev.ybonnetain.kintro.android.views.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import dev.ybonnetain.kintro.android.R
import dev.ybonnetain.kintro.android.helpers.ColorPalette
import dev.ybonnetain.kintro.android.helpers.typography

@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Column() {
                Spacer(modifier = Modifier.height(20.dp))
                Row() {
                    Column() {
                        Text(text = "Hello", style = typography.h1, color = Color.Black)
                        Text(text = "Compose + KMP", style = typography.h1, color = Color.Black)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Image(painterResource(id = R.drawable.compose), contentDescription = null)
                }
            }
        },
        backgroundColor = ColorPalette.orange,
        contentColor = Color.White,
        elevation = 0.dp,
        modifier = Modifier.heightIn(min = 110.dp, max = 110.dp) // TODO wrap content
    )
}

//@Preview(showBackground = true)
//@Composable
//fun TopBarPreview() {
//    TopBar()
//}
