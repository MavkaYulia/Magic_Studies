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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mavka.magicstudiesapp.R
import com.mavka.magicstudiesapp.domain.models.PathModel
import com.mavka.magicstudiesapp.domain.models.SubQuest
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicAddButtonExpanded
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicAddQuestDialog
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicIconSelectionDialog
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicQuestCard
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicTabHeader
import com.mavka.magicstudiesapp.presentation.theme.ui.ColorPalette
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicStudiesAppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun QuestsScreen(
    onQuestClick: (Int) -> Unit,
    viewModel: QuestsViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    QuestsScreenContent(
        uiState = uiState,
        onAddQuest = { title, icon, color, subQuests ->
            viewModel.addQuest(
                title = title,
                icon = icon,
                color = ColorPalette.getIndex(color),
                subQuests = subQuests
            )
        },
        onQuestClick
    )
}

@Composable
fun QuestsScreenContent(
    uiState: QuestsUiState,
    onAddQuest: (
        title: String,
        icon: Int,
        color: Color,
        subQuests: List<SubQuest>
    ) -> Unit,
    onQuestClick: (Int) -> Unit
) {
    var showNameDialog by remember { mutableStateOf(false) }
    var showIconDialog by remember { mutableStateOf(false) }
    var tempName by remember { mutableStateOf("") }
    var tempColor by remember { mutableStateOf(Color.Unspecified) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(
                vertical = dimensionResource(R.dimen.padding_small),
                horizontal = dimensionResource(R.dimen.padding_medium)
            )
    ) {

        MagicTabHeader(
            title = stringResource(R.string.tab_title),
            subTitle = stringResource(
                id = R.string.subtitle_quests,
                uiState.totalQuestsCount,
                uiState.remainingQuestsCount
            )
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_large)))

        MagicAddButtonExpanded(
            label = stringResource(R.string.new_quest),
            onClick = { showNameDialog = true },
            containerColor = MaterialTheme.colorScheme.secondary
        )

        if (showNameDialog) {
            MagicAddQuestDialog(
                onDismiss = { showNameDialog = false },
                onNext = { name, color ->
                    tempName = name
                    tempColor = color
                    showNameDialog = false
                    showIconDialog = true
                }
            )
        }

        if (showIconDialog) {
            MagicIconSelectionDialog(
                availableIcons = uiState.availableIcons,
                onDismiss = { showIconDialog = true },
                onSelect = { icon ->
                    onAddQuest(
                        tempName,
                        icon,
                        tempColor,
                        listOf()
                    )
                    showIconDialog = false
                }
            )
        }

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            items(uiState.quests) { quest ->

                MagicQuestCard(
                    pathModel = quest,
                    onDetailsClicked = { onQuestClick(quest.id) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun QuestScreenPreview() {

    val mockQuests = listOf(
        PathModel(
            title = "Quest1",
            icon = R.drawable.img_magic_9,
            subQuests = listOf(
                SubQuest(name = "SubQuest1", isDone = true, plannedTime = 2),
                SubQuest(name = "SubQuest2", isDone = false, plannedTime = 6)
            ),
            color = 4
        ),
        PathModel(
            title = "Quest2",
            icon = R.drawable.img_magic_9,
            subQuests = listOf(
                SubQuest(name = "SubQuest1", isDone = true, plannedTime = 2)
            ),
            color = 2
        ),
        PathModel(
            title = "Quest3",
            icon = R.drawable.img_magic_9,
            subQuests = emptyList(),
            color = 6
        )
    )
    MagicStudiesAppTheme {
        QuestsScreenContent(
            uiState = QuestsUiState(
                quests = mockQuests,
                3, 6, isLoading = false
            ),
            onAddQuest = { _, _, _, _ -> },
            {}
        )
    }
}
