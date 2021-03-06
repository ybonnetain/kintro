package dev.ybonnetain.kintro.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import dev.ybonnetain.kintro.android.R
import dev.ybonnetain.kintro.android.styles.KintroTheme
import dev.ybonnetain.kintro.android.styles.Scale
import dev.ybonnetain.kintro.android.styles.typography
import dev.ybonnetain.kintro.android.views.layout.TopBar

import org.koin.androidx.compose.getViewModel

@Composable
fun CounterScreen(viewModel: CounterViewModel = getViewModel()) {
    var menuVisible by remember { mutableStateOf(false) }

    fun toggleMenu() { menuVisible = !menuVisible }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .wrapContentSize(Alignment.Center),
    ) {

        TopBar("Counter")

        Text(
            text = "Here is the Fibonacci counter aimed at demonstrating algorythm sharing with Kotlin",
            style = typography.subtitle2,
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(Scale.base)
        )

        Spacer(modifier = Modifier.height(Scale.plus1))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            CounterCard(viewModel)
            Spacer(modifier = Modifier.width(Scale.plus1))
            CounterNextCard(viewModel)
        }

        Box(modifier = Modifier
            .padding(Scale.plus2)
            .offset(x = (-25).dp, y = (-80).dp)) {
            Menu(menuVisible, toggleMenu = ::toggleMenu, viewModel)
            FloatingActionButton(
                onClick = { toggleMenu() },
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.onSurface
            ) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "counter options")
            }
        }
        Spacer(modifier = Modifier.fillMaxHeight())
    }
}

@Composable
fun CounterCard(viewModel: CounterViewModel) {
    val count = viewModel.observer.collectAsState()

    Card(
        elevation = 0.dp,
        shape = RoundedCornerShape(Scale.base),
        backgroundColor = MaterialTheme.colors.primary,
    ) {
        Column(
            modifier = Modifier.padding(Scale.plus1),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text ="Count",
                style = typography.h2,
                color = MaterialTheme.colors.onPrimary,
            )
            Text(
                text = count.value.toString(),
                style = typography.h1,
                color = MaterialTheme.colors.onPrimary,
            )
            Text(
                text = "thing(s)",
                style = typography.body1,
                color = MaterialTheme.colors.onPrimary,
            )
        }
    }
}

@Composable
fun Menu(expanded: Boolean, toggleMenu: () -> Unit, viewModel: CounterViewModel) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { toggleMenu() },
        modifier = Modifier.width(200.dp)
    ) {
        DropdownMenuItem(onClick = { /*  */ }, enabled = false) {
            Text("Unavailable action", style = typography.body2)
        }
        DropdownMenuItem(
            onClick = {
                viewModel.decrement()
                toggleMenu()
            }) {
            Text("Previous term", style = typography.body2)
        }
        Divider()
        DropdownMenuItem(
            onClick = {
                viewModel.reset()
                toggleMenu()
            }) {
            Text("Reset counter", style = typography.body2)
        }
    }
}

@Composable
fun CounterNextCard(viewModel: CounterViewModel) {
    Card(
        elevation = 0.dp,
        shape = RoundedCornerShape(Scale.base),
        backgroundColor = Color.White,
    ) {
        Box(modifier = Modifier.clickable { viewModel.increment() }) {
            Column(
                modifier = Modifier.padding(Scale.plus1),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Next",
                    style = typography.h2,
                    color = Color.Black
                )
                Image(
                    painterResource(id = R.drawable.next),
                    contentDescription = null,
                )
                Text(
                    text = "Fibonacci",
                    style = typography.body1,
                    color = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CounterScreenPreview() {
    KintroTheme(darkTheme = false) {
        CounterScreen(viewModel = CounterViewModel(CounterMock()))
    }
}

@Preview(showBackground = true)
@Composable
fun CounterScreenDarkPreview() {
    KintroTheme(darkTheme = true) {
        CounterScreen(viewModel = CounterViewModel(CounterMock()))
    }
}