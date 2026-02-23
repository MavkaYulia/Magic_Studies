package com.mavka.magicstudiesapp.presentation.screens.quests

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddReaction
import androidx.compose.material.icons.filled.Forest
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Shield
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mavka.magicstudiesapp.R
import com.mavka.magicstudiesapp.domain.models.QuestModel
import com.mavka.magicstudiesapp.domain.models.SubQuest
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicAddButton
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicDialog
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicQuestCard
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicText
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicTitle
import com.mavka.magicstudiesapp.presentation.theme.ui.Magic
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicStudiesAppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun QuestsScreen(
    viewModel: QuestsViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val countSubQuest = uiState.quests.sumOf { quest ->
        quest.subQuests.count { !it.isDone }
    }
    val countQuest = uiState.quests.size
    QuestsScreenContent(
        uiState = uiState,
        onAddQuest = { title, icon, order, subQuests ->
            viewModel.addQuest(
                title = title,
                icon = icon,
                order = order,
                subQuests = subQuests
            )
        },
        countSubQuest = countSubQuest,
        countQuest = countQuest
    )
}

@Composable
fun QuestsScreenContent(
    uiState: QuestUiState,
    onAddQuest: (title: String, icon: ImageVector, order: Int, subQuests: List<SubQuest>) -> Unit,
    countSubQuest: Int,
    countQuest: Int
) {
    var showMagicDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Magic.colors.Parchment)
            .padding(dimensionResource(R.dimen.standard_padding))
    ) {

        MagicTitle(stringResource(R.string.tab_title))

        MagicText(
            stringResource(
                id = R.string.subtitle_quests,
                countQuest,
                countSubQuest
            )
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.large_padding)))

        MagicAddButton("New Subject", { showMagicDialog = true })

        if (showMagicDialog) {
            MagicDialog(
                onDismiss = {
                    showMagicDialog = false
                },
                onCreate = { subjectName ->
                    onAddQuest(
                        subjectName,
                        Icons.Default.AddReaction,
                        5, //todo() add to the end
                        listOf()
                    )

                    showMagicDialog = false
                }
            )
        }

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.large_padding)))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_padding))
        ) {
            items(uiState.quests) { quest ->
                MagicQuestCard(quest)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun QuestScreenPreview() {

    val mockQuests = listOf(
        QuestModel(
            title = "Quest1",
            icon = Icons.Default.Science,
            order = 1,
            subQuests = listOf(
                SubQuest(name = "SubQuest1", isDone = true, plannedTime = 2),
                SubQuest(name = "SubQuest2", isDone = true, plannedTime = 2)
            )
        ),
        QuestModel(
            title = "Quest2",
            icon = Icons.Default.Shield,
            order = 2,
            subQuests = listOf(
                SubQuest(name = "SubQuest1", isDone = true, plannedTime = 2)
            )
        ),
        QuestModel(
            title = "Quest3",
            icon = Icons.Default.Forest,
            order = 3,
            subQuests = emptyList()
        )
    )
    MagicStudiesAppTheme {
        QuestsScreenContent(
            uiState = QuestUiState(quests = mockQuests, isLoading = false, errorMessage = null),
            onAddQuest = { _, _, _, _ -> },
            countSubQuest = mockQuests.sumOf { it.subQuests.count { sub -> !sub.isDone } },
            countQuest = mockQuests.size
        )
    }
}
