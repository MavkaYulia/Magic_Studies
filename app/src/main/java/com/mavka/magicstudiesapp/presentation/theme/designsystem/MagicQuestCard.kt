package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
    subQuestStatus: Pair<Int, Int>,
    progress: Float,
    icon: ImageVector,
    spentTime: Int,
    subQuests: List<SubQuest>,
    onDeleteSubQuest: (subQuestId: Int) -> Unit,
    subQuestName: String,
    onSubQuestNameChange: (String) -> Unit,
    onAddSubQuest: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(true) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded },
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
                MagicIconBadge(icon = icon)

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
                    MagicProgressBar(
                        modifier = Modifier.padding(end = dimensionResource(R.dimen.padding_small)),
                        progress = progress
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${spentTime}H",
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
                                onDeleteClick = { onDeleteSubQuest(subQuest.id) }
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
                                onSubQuestNameChange(it)
                                if (it.isNotBlank()) isError = false
                            },
                            hintText = stringResource(R.string.hint_add_subquest),
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
                                    onAddSubQuest(subQuestName)
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
            order = 1,
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
        progress = 0.5f,
        icon = quest.icon,
        spentTime = spentTime,
        subQuests = quest.subQuests,
        onDeleteSubQuest = {},
        subQuestName = "text",
        onSubQuestNameChange = {},
        onAddSubQuest = {},
        modifier = Modifier
    )
}
