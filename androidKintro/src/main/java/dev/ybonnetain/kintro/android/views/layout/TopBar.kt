package dev.ybonnetain.kintro.android.views.layout

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import dev.ybonnetain.kintro.android.styles.KintroTheme
import dev.ybonnetain.kintro.android.styles.typography


@Composable
fun OverflowMenu(content: @Composable () -> Unit) {
    var showMenu by remember { mutableStateOf(false) }

    IconButton(onClick = {
        showMenu = !showMenu
    }) {
        Icon(
            imageVector = Icons.Outlined.MoreVert,
            contentDescription = stringResource(dev.ybonnetain.kintro.android.R.string.more),
        )
    }
    DropdownMenu(
        expanded = showMenu,
        onDismissRequest = {
            showMenu = false
        }
    ) {
        content()
    }
}

@Composable
fun TopBar(title: String, actions: (@Composable () -> Unit)? = null) {

    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
        elevation = 0.dp,
        modifier = Modifier
            .heightIn(min = 100.dp, max = 100.dp) // FIXME does not wrap content
            .padding(horizontal = 0.dp, vertical = 24.dp),
        title = {
            Row() {
                Text(
                    text = title,
                    style = typography.h1
                )
            }
        },
        actions = {
            if (actions != null) {
                actions()
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
