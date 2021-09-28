package dev.ybonnetain.kintro.android.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TodosScreen() {
    Text("Todos")
}

@Preview(showBackground = true)
@Composable
fun TodosScreenPreview() {
    TodosScreen()
}
