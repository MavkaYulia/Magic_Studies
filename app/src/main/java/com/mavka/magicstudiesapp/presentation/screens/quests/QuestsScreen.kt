package com.mavka.magicstudiesapp.presentation.screens.quests

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicTitle

@Composable
fun QuestsScreen(
    viewModel: QuestsViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    Column() {
        MagicTitle("Yulia What going?")
        MagicTitle("Юлія Що не так?")
    }

}

@Preview(showBackground = true)
@Composable
fun QuestsScreenPreview() {

    QuestsScreen()
}
