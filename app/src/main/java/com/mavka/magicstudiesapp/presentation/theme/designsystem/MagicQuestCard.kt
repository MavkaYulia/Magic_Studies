package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mavka.magicstudiesapp.R
import com.mavka.magicstudiesapp.domain.models.Difficulty
import com.mavka.magicstudiesapp.domain.models.QuestModel
import com.mavka.magicstudiesapp.domain.models.SubQuest
import com.mavka.magicstudiesapp.domain.models.time
import com.mavka.magicstudiesapp.presentation.theme.ui.Magic
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicMaterialTypography

@Composable
fun MagicQuestCard(questModel: QuestModel) {

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = Magic.shape.card,
        colors = CardDefaults.cardColors(containerColor = Magic.colors.OldPaper),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(
                    horizontal = dimensionResource(R.dimen.standard_padding),
                    vertical = dimensionResource(R.dimen.large_padding)
                )
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(dimensionResource(R.dimen.icon_size))
                    .background(Color.White.copy(alpha = 0.3f), shape = Magic.shape.icon),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = questModel.icon,
                    contentDescription = "Icon",
                    tint = Magic.colors.ForestGreen
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = questModel.title,
                        color = Magic.colors.ForestGreen,
                        style = MagicMaterialTypography.titleMedium
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${questModel.subQuests.size}/${questModel.getRemainingQuest()}",
                        color = Magic.colors.ForestGreen.copy(alpha = 0.7f),
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(8.dp)
                        .background(Magic.colors.OldPaper, RoundedCornerShape(4.dp))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(1f) // todo()
                            .fillMaxHeight()
                            .background(Magic.colors.FadedGold, RoundedCornerShape(4.dp))
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) { //TODO() finish UI of time view
                Text(
                    text = " ${questModel.getSpendedTime()} H",
                    color = Magic.colors.ForestGreen,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Expand",
                    tint = Magic.colors.ForestGreen
                )
            }
        }
    }
}

fun QuestModel.getRemainingQuest() = subQuests.count { it.isDone }
fun QuestModel.getSpendedTime() = subQuests.sumOf { it.difficulty.time }


@Preview(showBackground = true)
@Composable
private fun MagicQuestCardPreview() {
    return MagicQuestCard(
        QuestModel(
            id = 939393, title = "title", icon = Icons.Default.AcUnit, subQuests = listOf(
                SubQuest(name = "subtask1", isDone = true, difficulty = Difficulty.MEDIUM),
                SubQuest(name = "subtask2", isDone = true, difficulty = Difficulty.HARD),
                SubQuest(name = "subtask3", isDone = false, difficulty = Difficulty.EASY)
            )
        )
    )
}
