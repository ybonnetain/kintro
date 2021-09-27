package dev.ybonnetain.kintro.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ybonnetain.kintro.android.R

import dev.ybonnetain.kintro.android.helpers.ColorPalette
import dev.ybonnetain.kintro.repositories.Counter
import org.koin.androidx.compose.getViewModel

@ExperimentalMaterialApi
@Composable
fun CounterScreen(viewModel: CounterViewModel = getViewModel()) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorPalette.orange)
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top // FIXME
    ) {
        Text(
            text = "The counter is basically what comes right after the hello world",
            fontWeight = FontWeight.W400,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(12.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            CounterCard(viewModel)
            Spacer(modifier = Modifier.width(30.dp))
            CounterNextCard(viewModel)
        }
    }
}

@Composable
fun CounterCard(viewModel: CounterViewModel) {
    val count = viewModel.observer.collectAsState()
    Card(
        elevation = 0.dp,
        shape = RoundedCornerShape(20.dp),
        backgroundColor = ColorPalette.darkOrange,
    ) {
        Column(
            modifier = Modifier.padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Count", fontWeight = FontWeight.W700, fontSize = 32.sp)
            Text(count.value.toString(), fontWeight = FontWeight.Black, fontSize = 40.sp)
            Text("thing(s)", color = Color.White, fontSize = 20.sp)
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun CounterNextCard(viewModel: CounterViewModel) {
    Card(
        elevation = 0.dp,
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Color.White,
        onClick = { viewModel.increment() }
    ) {
        Column(
            modifier = Modifier.padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Next", fontWeight = FontWeight.W700, fontSize = 32.sp)
            Image(painterResource(id = R.drawable.next), contentDescription = null)
            Text("Fibonacci", fontSize = 20.sp)
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun CounterScreenPreview() {
    CounterScreen(viewModel = CounterViewModel(repository = Counter()))
}
