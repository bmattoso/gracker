package br.com.gracker.history.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HistoryScreenContent() {
    Column(
        modifier = Modifier.background(Color.White),
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = "No game history",
            color = Color.Black,
        )
    }
}
