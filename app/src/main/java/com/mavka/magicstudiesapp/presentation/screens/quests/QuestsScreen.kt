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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import com.mavka.magicstudiesapp.R
import com.mavka.magicstudiesapp.domain.models.QuestModel
import com.mavka.magicstudiesapp.domain.models.SubQuest
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicAddButtonExpanded
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicAddDialog
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicQuestCard
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicText
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicTitle
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicStudiesAppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun QuestsScreen(
    viewModel: QuestsViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    QuestsScreenContent(
        uiState = uiState,
        onAddQuest = { title, icon, subQuests ->
            viewModel.addQuest(
                title = title,
                icon = icon,
                subQuests = subQuests
            )
        }
    )
}

@Composable
fun QuestsScreenContent(
    uiState: QuestUiState,
    onAddQuest: (
        title: String,
        icon: ImageVector,
        subQuests: List<SubQuest>
    ) -> Unit,
) {
    var showMagicDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(dimensionResource(R.dimen.padding_medium))
    ) {

        MagicTitle(stringResource(R.string.tab_title))

        Spacer(modifier = Modifier.height(4.dp))

        MagicText(
            text = stringResource(
                id = R.string.subtitle_quests,
                uiState.quests.size,
                uiState.quests.sumOf { quest ->
                    quest.subQuests.count { !it.isDone }
                }
            ),
            style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f))
        )

        Spacer(modifier = Modifier.height(32.dp))

        MagicAddButtonExpanded(
            label = stringResource(R.string.new_quest),
            onClick = { showMagicDialog = true },
            containerColor = MaterialTheme.colorScheme.tertiary
        )

        if (showMagicDialog) {
            MagicAddDialog(
                onDismiss = { showMagicDialog = false },
                onCreate = { subjectName ->
                    onAddQuest(
                        subjectName,
                        Icons.Default.AddReaction,
                        listOf()
                    )
                    showMagicDialog = false
                }
            )
        }

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            items(uiState.quests) { quest ->

                MagicQuestCard(
                    questModel = quest,
                    onDetailsClicked = {}
                )
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
            subQuests = listOf(
                SubQuest(name = "SubQuest1", isDone = true, plannedTime = 2),
                SubQuest(name = "SubQuest2", isDone = false, plannedTime = 6)
            )
        ),
        QuestModel(
            title = "Quest2",
            icon = Icons.Default.Shield,
            subQuests = listOf(
                SubQuest(name = "SubQuest1", isDone = true, plannedTime = 2)
            )
        ),
        QuestModel(
            title = "Quest3",
            icon = Icons.Default.Forest,
            subQuests = emptyList()
        )
    )
    MagicStudiesAppTheme {
        QuestsScreenContent(
            uiState = QuestUiState(quests = mockQuests, isLoading = false, errorMessage = null),
            onAddQuest = { _, _, _ -> },

        )
    }
}
