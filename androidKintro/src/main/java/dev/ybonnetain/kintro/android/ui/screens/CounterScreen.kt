package dev.ybonnetain.kintro.android.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.ybonnetain.kintro.android.helpers.ColorScheme
import org.koin.androidx.compose.getViewModel

@Composable
fun CounterScreen() {
    val viewModel = getViewModel<CounterViewModel>()

    Column(
        modifier = Modifier
            .fillMaxSize()

            .background(ColorScheme.counter)
            .wrapContentSize(Alignment.Center)
    ) {
        var count by remember { mutableStateOf(0) }
        Text(
            text = count.toString(),
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
        Button(onClick = {
            count = viewModel.increment()
        }) {
            Text(text = "increment counter")
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun CounterScreenPreview() {
//    CounterScreen()
//}
