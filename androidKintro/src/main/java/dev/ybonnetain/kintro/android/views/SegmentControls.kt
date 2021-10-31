package dev.ybonnetain.kintro.android.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import dev.ybonnetain.kintro.android.styles.KintroTheme
import dev.ybonnetain.kintro.android.styles.Scale

@Composable
fun SegmentControls(
    items: List<String>,
    currentIndex: Int,
    callback: ((index: Int) -> Unit)? = null
) {
    val index = remember{ mutableStateOf(currentIndex) }

    Row(
        modifier = Modifier
            .padding(Scale.base)
            .height(30.dp)
    ) {

        items.forEachIndexed { i, item ->
            Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .width(80.dp)
                .height(30.dp)
                .background(color = if (index.value == i) MaterialTheme.colors.primary else Color.Transparent)
                .clickable {
                    index.value = i
                    callback?.invoke(index.value)
                }
            ) {
                Text(
                    text = item.lowercase(),
                    color = if (index.value == i) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onBackground
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodosScreenLightPreview() {
    KintroTheme(darkTheme = false) {
        SegmentControls(
            items = listOf("Hello", "Salut", "Halo"),
            currentIndex = 0
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TodosScreenDarkPreview() {
    KintroTheme(darkTheme = true) {
        SegmentControls(
            items = listOf("Hello", "Salut", "Halo"),
            currentIndex = 0
        )
    }
}