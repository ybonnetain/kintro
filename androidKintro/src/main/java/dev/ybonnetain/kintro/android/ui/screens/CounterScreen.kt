package dev.ybonnetain.kintro.android.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.ybonnetain.kintro.android.helpers.ColorPalette
import org.koin.androidx.compose.getViewModel

@Composable
fun CounterScreen() {
    val viewModel = getViewModel<CounterViewModel>()
    val count = viewModel.observer.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorPalette.orange)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = count.value.toString(),
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
        Button(onClick = { viewModel.increment() }) {
            Text(text = "increment counter")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            CounterCard()
            CounterCard()
        }

    }
}

@Composable
fun CounterCard() {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .background(ColorPalette.darkOrange)
            .padding(30.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Count", fontWeight = FontWeight.W700)
            Text("1", fontWeight = FontWeight.Black, fontSize = 40.sp)
            Text("thing(s)", color = Color.Gray)
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun CounterScreenPreview() {
//    CounterScreen()
//}
