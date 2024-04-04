package br.com.gracker.snapshot

import app.cash.paparazzi.Paparazzi
import br.com.gracker.history.ui.HistoryScreenContent
import org.junit.Rule
import org.junit.Test

class ComposeTest {
    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun historyScreenContent() {
        paparazzi.snapshot {
            HistoryScreenContent()
        }
    }
}
