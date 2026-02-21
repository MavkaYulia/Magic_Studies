package com.mavka.magicstudiesapp.presentation.screens.quests

import android.R.attr.name
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mavka.magicstudiesapp.presentation.theme.designsystem.TitleText

@Composable
fun QuestsScreen(
    viewModel: QuestsViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    Column() {
        TitleText("Yulia What going?")
        TitleText("Юлія Що не так?")
    }

}

@Preview(showBackground = true)
@Composable
fun QuestsScreenPreview() {

    QuestsScreen()
}
