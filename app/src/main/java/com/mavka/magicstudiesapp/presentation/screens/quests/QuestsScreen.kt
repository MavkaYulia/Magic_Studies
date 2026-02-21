package com.mavka.magicstudiesapp.presentation.screens.quests

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mavka.magicstudiesapp.R
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicAddButton
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicText
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicTitle
import com.mavka.magicstudiesapp.presentation.theme.ui.Magic

@Composable
fun QuestsScreen(
    viewModel: QuestsViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Magic.colors.Parchment)
            .padding(dimensionResource(R.dimen.standard_padding))
    ) {

        MagicTitle(stringResource(R.string.tab_title))


        val quests = uiState.quests.size
        val tasks = viewModel.getSubQuestsSize()

        MagicText(
            stringResource(
                id = R.string.subtitle_quests,
                quests,
                tasks
            )
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.large_padding)))

        MagicAddButton("New Subject", {  })

    }

}

@Preview(showBackground = true)
@Composable
fun QuestsScreenPreview() {

    QuestsScreen()
}
