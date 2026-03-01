package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mavka.magicstudiesapp.R
import com.mavka.magicstudiesapp.domain.models.QuestModel
import com.mavka.magicstudiesapp.domain.models.SubQuest
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicMaterialColor
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicMaterialShapes
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicMaterialTypography

@Composable
fun MagicQuestCard(
    title: String,
    icon: ImageVector,
    subQuestStatus: Pair<Int, Int>,
    spentTime: Int,
    subQuests: List<SubQuest>,
    subQuestName: String,
    onChangeSubQuestName: (String) -> Unit,
    isCompleteSubQuest: (subQuest: SubQuest) -> Unit,
    onDeleteSubQuest: (subQuestId: Int) -> Unit,
    onAddSubQuest: () -> Unit,
    onDeleteQuest: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    var showDeleteIcon by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = {
                    if (showDeleteIcon) showDeleteIcon = false else isExpanded = !isExpanded
                },
                onLongClick = {
                    showDeleteIcon = true
                }
            ),
        shape = MagicMaterialShapes.large,
        colors = CardDefaults.cardColors(containerColor = MagicMaterialColor.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(R.dimen.elevation))
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(
                        horizontal = dimensionResource(R.dimen.padding_medium),
                        vertical = dimensionResource(R.dimen.padding_large)
                    )
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(contentAlignment = Alignment.Center) {
                    this@Row.AnimatedVisibility(
                        visible = !showDeleteIcon,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        MagicIconBadge(icon = icon)
                    }

                    this@Row.AnimatedVisibility(
                        visible = showDeleteIcon,
                        enter = scaleIn() + fadeIn(),
                        exit = scaleOut() + fadeOut()
                    ) {
                        IconButton(
                            onClick = {
                                onDeleteQuest()
                                showDeleteIcon = false
                            },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = MagicMaterialColor.error.copy(alpha = 0.1f)
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = MagicMaterialColor.error
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.width(dimensionResource(R.dimen.spacing_medium)))

                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = title,
                            color = MagicMaterialColor.primary,
                            style = MagicMaterialTypography.titleMedium
                        )
                        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.spacing_small)))
                        Text(
                            text = "${subQuestStatus.first}/${subQuestStatus.second}",
                            color = MagicMaterialColor.primary.copy(alpha = 0.7f),
                            style = MagicMaterialTypography.titleMedium
                        )
                    }

                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_tiny)))

                    val total = subQuestStatus.second.coerceAtLeast(1)
                    val remaining = subQuestStatus.first.coerceIn(0, total)
                    val completed = total - remaining

                    MagicProgressBar(
                        modifier = Modifier.padding(end = dimensionResource(R.dimen.padding_small)),
                        progress = completed.toFloat() / total.toFloat()
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    val hours = spentTime / 60
                    val minutes = spentTime % 60
                    Text(
                        text = if (hours > 0) "${hours}h ${minutes}m" else "${minutes}m",
                        color = MagicMaterialColor.primary,
                        style = MagicMaterialTypography.titleMedium
                    )
                    Spacer(modifier = Modifier.width(dimensionResource(R.dimen.spacing_small)))
                    Icon(
                        imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = MagicMaterialColor.primary
                    )
                }
            }

            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically(animationSpec = tween(300)) + fadeIn(),
                exit = shrinkVertically(animationSpec = tween(300)) + fadeOut()
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = dimensionResource(R.dimen.padding_medium))
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_small))
                ) {

                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)),
                        thickness = 0.5.dp,
                        color = MagicMaterialColor.primary
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_medium)))

                    if (subQuests.isNotEmpty()) {
                        subQuests.forEach { subQuest ->
                            MagicSubQuestCard(
                                subQuest = subQuest,
                                onDelete = { onDeleteSubQuest(subQuest.id) },
                                isComplete = {
                                    isCompleteSubQuest(it)
                                }
                            )
                        }

                        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_small)))

                        HorizontalDivider(
                            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)),
                            thickness = 0.5.dp,
                            color = MagicMaterialColor.primary
                        )
                    }

                    var isError by remember { mutableStateOf(false) }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_small)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        MagicTextField(
                            value = subQuestName,
                            onValueChange = {
                                onChangeSubQuestName(it)
                                if (it.isNotBlank()) isError = false
                            },
                            hintText = stringResource(R.string.new_subquest),
                            isError = isError,
                            modifier = Modifier
                                .weight(1f)
                                .then(
                                    if (isError) Modifier.border(
                                        width = 1.dp,
                                        color = MagicMaterialColor.error,
                                        shape = MagicMaterialShapes.small
                                    ) else Modifier
                                )
                        )

                        MagicAddButtonIcon(
                            onClick = {
                                if (subQuestName.isNotBlank()) {
                                    onAddSubQuest()
                                    isError = false
                                } else {
                                    isError = true
                                }
                            },
                            modifier = Modifier.size(dimensionResource(R.dimen.height_large))
                        )
                    }

                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_medium)))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MagicQuestCardPreview() {
    val quest =
        QuestModel(
            title = "title1",
            icon = Icons.Default.AcUnit,
            subQuests = listOf(
                SubQuest(name = "subtask1", isDone = true, plannedTime = 8),
                SubQuest(name = "subtask2", isDone = true, plannedTime = 4),
                SubQuest(name = "subtask3", isDone = false, plannedTime = 9)
            )
        )

    val spentTime = quest.subQuests.filter { it.isDone }.sumOf { it.plannedTime }

    MagicQuestCard(
        title = quest.title,
        subQuestStatus = Pair(quest.subQuests.count {
            !it.isDone
        }, quest.subQuests.size),
        icon = quest.icon,
        spentTime = spentTime,
        subQuests = quest.subQuests,
        subQuestName = "",
        onChangeSubQuestName = {},
        onDeleteSubQuest = {},
        onAddSubQuest = {},
        onDeleteQuest = {},
        isCompleteSubQuest = {},
        modifier = Modifier
    )
}
