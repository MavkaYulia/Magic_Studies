package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mavka.magicstudiesapp.R
import com.mavka.magicstudiesapp.domain.models.QuestModel
import com.mavka.magicstudiesapp.domain.models.SubQuest
import com.mavka.magicstudiesapp.presentation.theme.ui.ColorPalette
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicColor
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicStudiesAppTheme

@Composable
fun MagicQuestCard(
    questModel: QuestModel,
    onDetailsClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onDetailsClicked() },
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = androidx.compose.foundation.BorderStroke(
            dimensionResource(R.dimen.border),
            MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
        )
    ) {
        Row(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_small))
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MagicIconPlate(icon = questModel.icon, size = R.dimen.icon_size_extra_large)

            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_medium)))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = dimensionResource(R.dimen.padding_small))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
                ) {
                    MagicText(
                        text = questModel.title.uppercase(),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    if (questModel.subQuests.isNotEmpty()) {
                        MagicPriorityBadge(questModel.getPriority)
                    }
                }

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))

                MagicProgressBar(progress = questModel.progress, color = questModel.color)

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
                ) {
                    MagicText(
                        text = stringResource(
                            R.string.tasks_count,
                            questModel.completedSubQuestsCount,
                            questModel.totalSubQuestsCount
                        ),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_tiny))
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_clock),
                            contentDescription = null,
                            modifier = Modifier.size(dimensionResource(R.dimen.icon_size_small) * 0.8f),
                            tint = MagicColor.IronInk.copy(alpha = 0.7f)
                        )
                        MagicText(
                            text = stringResource(
                                R.string.hours_format,
                                questModel.totalSpentTime
                            ),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                        )
                    }

                    MagicText(
                        text = stringResource(
                            R.string.remaining_count,
                            questModel.totalSubQuestsCount - questModel.completedSubQuestsCount
                        ),
                        style = MaterialTheme.typography.bodySmall,
                        color = questModel.color
                    )
                }
            }

            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_small)))

            Icon(
                painter = painterResource(id = R.drawable.ic_chevron_right),
                contentDescription = null,
                modifier = Modifier.size(dimensionResource(R.dimen.icon_size_medium)),
                tint = MagicColor.IronInk.copy(alpha = 0.3f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MagicQuestCardPreview() {
    MagicStudiesAppTheme {
        Box(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
            MagicQuestCard(
                questModel = QuestModel(
                    title = "Alchemy",
                    icon = R.drawable.img_magic_9,
                    subQuests = listOf(
                        SubQuest(isDone = true, name = "Task 1", plannedTime = 10f),
                        SubQuest(isDone = true, name = "Task 2", plannedTime = 5f),
                        SubQuest(isDone = false, name = "Task 3", plannedTime = 5f),
                        SubQuest(isDone = false, name = "Task 4", plannedTime = 4f)
                    ),
                    color = ColorPalette.getAt(1)
                ),
                onDetailsClicked = {},

                )
        }
    }
}
