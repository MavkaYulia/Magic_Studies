package com.mavka.magicstudiesapp.presentation.screens.quests.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mavka.magicstudiesapp.R
import com.mavka.magicstudiesapp.domain.models.Priority
import com.mavka.magicstudiesapp.domain.models.QuestModel
import com.mavka.magicstudiesapp.domain.models.SubQuest
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicAddButtonIcon
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicFilterSection
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicIconPlate
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicModalBottomSheet
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicProgressBar
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicSectionTitle
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicSubQuestCard
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicTitle
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicTopAppBar
import com.mavka.magicstudiesapp.presentation.theme.ui.ColorPalette
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicStudiesAppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(
    onBack: () -> Unit,
    viewModel: DetailsViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val filter by viewModel.filter.collectAsState()
    val hideDone by viewModel.hideDone.collectAsState()

    DetailsScreenContent(
        uiState = uiState,
        filter = filter,
        hideDone = hideDone,
        onBack = onBack,
        onFilterSelected = viewModel::setFilter,
        onHideDoneToggle = viewModel::toggleHideDone,
        onToggleSubQuestDone = viewModel::toggleSubQuestDone,
        onDeleteSubQuest = viewModel::deleteSubQuest,
        onAddSubQuest = viewModel::addSubQuest,
        onDeleteQuest = viewModel::deleteQuest
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreenContent(
    uiState: QuestModel?,
    filter: QuestFilter,
    hideDone: Boolean,
    onBack: () -> Unit,
    onFilterSelected: (QuestFilter) -> Unit,
    onHideDoneToggle: () -> Unit,
    onToggleSubQuestDone: (SubQuest) -> Unit,
    onDeleteSubQuest: (Int) -> Unit,
    onAddSubQuest: (String, Float, Priority) -> Unit,
    onDeleteQuest: (Int) -> Unit,
) {
    var newTaskName by remember { mutableStateOf("") }
    var newTaskPriority by remember { mutableStateOf(Priority.NORMAL) }
    var newTaskHours by remember { mutableStateOf("") }

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState,
            containerColor = MaterialTheme.colorScheme.background
        ) {
            MagicModalBottomSheet(
                name = newTaskName,
                onNameChange = { newTaskName = it },
                priority = newTaskPriority,
                onPriorityChange = { newTaskPriority = it },
                hours = newTaskHours,
                onHoursChange = { newTaskHours = it },
                onAdd = {
                    onAddSubQuest(
                        newTaskName,
                        newTaskHours.toFloatOrNull() ?: 0f,
                        newTaskPriority
                    )
                    newTaskName = ""
                    newTaskHours = ""
                    newTaskPriority = Priority.NORMAL
                    showBottomSheet = false
                },
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_medium))
                    .padding(bottom = dimensionResource(R.dimen.padding_large))
            )
        }
    }

    Scaffold(
        topBar = {
            MagicTopAppBar(
                title = stringResource(R.string.tab_quests).uppercase(),
                onBackClick = onBack,
                actions = {
                    IconButton(onClick = {
                        onBack()
                        uiState?.id?.let {
                            onDeleteQuest(it)
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.DeleteOutline,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            MagicAddButtonIcon(onClick = { showBottomSheet = true })
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        uiState?.let { quest ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = dimensionResource(R.dimen.padding_medium)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
            ) {
                item {
                    SubQuestHeader(
                        icon = quest.icon,
                        title = quest.title,
                        studiedTime = quest.totalSpentTime,
                        tasksDone = quest.completedSubQuestsCount,
                        totalTasks = quest.totalSubQuestsCount
                    )
                }

                item {
                    ProgressSection(progress = quest.progress)
                }

                item {
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                }

                item {
                    MagicFilterSection(
                        selectedFilter = filter,
                        hideDone = hideDone,
                        onFilterSelected = onFilterSelected,
                        onHideDoneToggle = onHideDoneToggle
                    )
                }

                item {
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                }

                val activeTasks = quest.subQuests.filter { !it.isDone }
                val completedTasks = quest.subQuests.filter { it.isDone }

                if (activeTasks.isNotEmpty()) {
                    item {
                        MagicSectionTitle(
                            title = stringResource(R.string.active).uppercase(),
                            count = activeTasks.size
                        )
                    }
                    items(activeTasks) { task ->
                        MagicSubQuestCard(
                            task = task,
                            onToggleDone = { onToggleSubQuestDone(task) },
                            onDelete = { onDeleteSubQuest(task.id) }
                        )
                    }
                }

                if (completedTasks.isNotEmpty()) {
                    item {
                        MagicSectionTitle(
                            title = stringResource(R.string.completed).uppercase(),
                            count = completedTasks.size,
                            icon = Icons.Default.Star
                        )
                    }
                    items(completedTasks) { task ->
                        MagicSubQuestCard(
                            task = task,
                            onToggleDone = { onToggleSubQuestDone(task) },
                            onDelete = { onDeleteSubQuest(task.id) }
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))
                }
            }
        }
    }
}

@Composable
fun SubQuestHeader(
    icon: Int,
    title: String,
    studiedTime: Float,
    tasksDone: Int,
    totalTasks: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(R.dimen.padding_small)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MagicIconPlate(icon = icon, size = R.dimen.icon_size_extra_large)

        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_medium)))

        Column {
            MagicTitle(
                title = title
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_clock),
                    contentDescription = null,
                    modifier = Modifier.size(dimensionResource(R.dimen.icon_size_small)),
                    tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                )
                Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_tiny)))
                Text(
                    text = stringResource(
                        R.string.hours_format,
                        studiedTime
                    ) + " " + stringResource(R.string.studied),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                )
                Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_medium)))
                Text(
                    text = stringResource(R.string.tasks_count, tasksDone, totalTasks),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                )
            }
        }
    }
}

@Composable
fun ProgressSection(progress: Float) {
    Column(verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_tiny))) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.quest_progress),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            )
            Text(
                text = "${(progress * 100).toInt()}%",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.outlineVariant
            )
        }
        MagicProgressBar(
            progress = progress,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailsScreenPreview() {
    MagicStudiesAppTheme {
        DetailsScreenContent(
            uiState = QuestModel(
                title = "Study Magic",
                icon = R.drawable.img_magic_9,
                subQuests = listOf(
                    SubQuest(
                        id = 1,
                        name = "Learn Fireball",
                        isDone = false,
                        plannedTime = 2f,
                        priority = Priority.URGENT
                    ),
                    SubQuest(
                        id = 2,
                        name = "Learn Levitation",
                        isDone = false,
                        plannedTime = 4f,
                        priority = Priority.NORMAL
                    )
                ),
                color = ColorPalette.getRandom()
            ),
            filter = QuestFilter.All,
            hideDone = false,
            onBack = {},
            onFilterSelected = {},
            onHideDoneToggle = {},
            onToggleSubQuestDone = {},
            onDeleteSubQuest = {},
            onAddSubQuest = { _, _, _ -> },
            onDeleteQuest = {}
        )
    }
}

