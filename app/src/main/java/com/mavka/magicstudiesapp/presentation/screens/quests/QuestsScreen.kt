package com.mavka.magicstudiesapp.presentation.screens.quests

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun QuestsScreen(
    viewModel: QuestsViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
}
