package dev.ybonnetain.kintro.android.ui.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ybonnetain.kintro.android.helpers.ColorScheme

import dev.ybonnetain.kintro.android.R

@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Column() {
                Spacer(modifier = Modifier.height(20.dp))
                Row() {
                    Column() {
                        Text(text = "Hello", fontSize = 28.sp, color = Color.Black)
                        Text(text = "Compose UI + KMP", fontSize = 28.sp, color = Color.Black)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Image(painterResource(id = R.drawable.compose), contentDescription = null)
                }
            }
        },
        backgroundColor = ColorScheme.counter,
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
