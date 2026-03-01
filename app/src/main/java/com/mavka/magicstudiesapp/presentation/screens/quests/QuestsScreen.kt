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
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicAddButtonExpanded
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicAddDialog
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicQuestCard
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicText
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicTimePicker
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicTitle
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicMaterialColor
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
        },
        onAddSubQuest = { questId, subName, plannedTime ->
            viewModel.addSubQuest(
                questId = questId,
                subName = subName,
                subPlannedTime = plannedTime
            )
        },
        updateSubQuest = { questId, subQuest ->
            viewModel.updateSubQuest(questId, subQuest)
        },
        onDeleteSubQuest = { subQuest ->
            viewModel.deleteSubQuest(subQuest)
        },
        onDeleteQuest = { questId ->
            viewModel.deleteQuest(questId)
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
    onAddSubQuest: (
        questId: Int,
        subName: String,
        plannedTime: Int
    ) -> Unit,
    updateSubQuest: (questId: Int, subquest: SubQuest) -> Unit,
    onDeleteSubQuest: (subQuestId: Int) -> Unit,
    onDeleteQuest: (Int) -> Unit
) {
    var showMagicDialog by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    var pendingQuestId by remember { mutableStateOf<Int?>(null) }
    var subQuestName by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MagicMaterialColor.background)
            .padding(dimensionResource(R.dimen.padding_medium))
    ) {

        MagicTitle(stringResource(R.string.tab_title))

        MagicText(
            text = stringResource(
                id = R.string.subtitle_quests,
                uiState.quests.size,
                uiState.quests.sumOf { quest ->
                    quest.subQuests.count { !it.isDone }
                }
            )
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))

        MagicAddButtonExpanded(stringResource(R.string.new_quest), { showMagicDialog = true })

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
                    title = quest.title,
                    subQuestStatus = Pair(
                        quest.subQuests.count { it.isDone },
                        quest.subQuests.size
                    ),
                    icon = quest.icon,
                    spentTime = quest.subQuests.filter { it.isDone }.sumOf { it.plannedTime },
                    subQuests = quest.subQuests,
                    subQuestName = subQuestName,
                    onChangeSubQuestName = { subQuestName = it },
                    isCompleteSubQuest = { updateSubQuest(quest.id, it) },
                    onDeleteSubQuest = { onDeleteSubQuest(it) },
                    onAddSubQuest = {
                        pendingQuestId = quest.id
                        showTimePicker = true
                    },
                    onDeleteQuest = { onDeleteQuest(quest.id) }
                )
            }
        }

        if (showTimePicker) {
            MagicTimePicker(
                onConfirmClick = { time ->
                    if (time != 0) {
                        pendingQuestId?.let { onAddSubQuest(it, subQuestName, time) }
                        showTimePicker = false
                        subQuestName = ""
                    }
                },
                onDismissClick = {
                    showTimePicker = false
                }
            )
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
            { _, _, _ -> },
            { _, _ -> },
            {},
            {}
        )
    }
}
