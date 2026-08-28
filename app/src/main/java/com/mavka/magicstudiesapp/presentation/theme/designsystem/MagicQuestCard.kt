package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Science
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mavka.magicstudiesapp.R
import com.mavka.magicstudiesapp.domain.models.QuestModel
import com.mavka.magicstudiesapp.domain.models.SubQuest
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicColor
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicMaterialColor
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicMaterialShapes
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicMaterialTypography

@Composable
fun MagicQuestCard(
    questModel: QuestModel,
    onDetailsClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val isUrgent= true //todo from subquest

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onDetailsClicked() },
        shape = MagicMaterialShapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MagicMaterialColor.surface
        ),
        border = androidx.compose.foundation.BorderStroke(1.dp, MagicColor.FadedGold.copy(alpha = 0.5f))
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MagicColor.Parchment)
                    .border(
                        width = 1.dp,
                        color = MagicColor.FadedGold.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = questModel.icon,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    tint = MagicColor.IronInk
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = questModel.title.uppercase(),
                        style = MagicMaterialTypography.titleMedium,
                        color = MagicMaterialColor.onSurface
                    )

                    if (isUrgent) {
                        MagicUrgentBadge()
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                MagicQuestProgressBar(progress = questModel.progress)

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = stringResource(
                            R.string.tasks_count,
                            questModel.completedSubQuestsCount,
                            questModel.totalSubQuestsCount
                        ),
                        style = MagicMaterialTypography.bodySmall,
                        color = MagicColor.IronInk.copy(alpha = 0.7f)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_clock),
                            contentDescription = null,
                            modifier = Modifier.size(14.dp),
                            tint = MagicColor.IronInk.copy(alpha = 0.7f)
                        )
                        Text(
                            text = stringResource(R.string.hours_format, 24.5f), // todo() Using hardcoded value for sample
                            style = MagicMaterialTypography.bodySmall,
                            color = MagicColor.IronInk.copy(alpha = 0.7f)
                        )
                    }

                    Text(
                        text = stringResource(
                            R.string.remaining_count,
                            questModel.totalSubQuestsCount - questModel.completedSubQuestsCount
                        ),
                        style = MagicMaterialTypography.bodySmall,
                        color = MagicColor.FadedGold
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                painter = painterResource(id = R.drawable.ic_chevron_right),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = MagicColor.IronInk.copy(alpha = 0.3f)
            )
        }
    }
}

@Composable
private fun MagicUrgentBadge(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MagicColor.UrgentRed.copy(alpha = 0.2f))
            .padding(horizontal = 10.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = stringResource(R.string.urgent),
            style = MagicMaterialTypography.labelSmall.copy(fontWeight = FontWeight.Bold),
            color = MagicColor.UrgentRed
        )
    }
}

@Composable
private fun MagicQuestProgressBar(
    progress: Float,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(10.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(MagicColor.IronInk.copy(alpha = 0.1f))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress)
                .height(10.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(MagicColor.FadedGold)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MagicQuestCardPreview() {
    MaterialTheme(
        colorScheme = MagicMaterialColor,
        typography = MagicMaterialTypography,
        shapes = MagicMaterialShapes
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            MagicQuestCard(
                questModel = QuestModel(
                    title = "Alchemy",
                    icon = Icons.Default.Science,
                    subQuests = listOf(
                        SubQuest(isDone = true, name = "Task 1", plannedTime = 10),
                        SubQuest(isDone = true, name = "Task 2", plannedTime = 5),
                        SubQuest(isDone = false, name = "Task 3", plannedTime = 5),
                        SubQuest(isDone = false, name = "Task 4", plannedTime = 4)
                    )
                ),
                onDetailsClicked = {},
            )
        }
    }
}
