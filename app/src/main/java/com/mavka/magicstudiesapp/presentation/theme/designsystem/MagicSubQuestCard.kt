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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.mavka.magicstudiesapp.R
import com.mavka.magicstudiesapp.domain.models.Priority
import com.mavka.magicstudiesapp.domain.models.SubQuest

import androidx.compose.ui.graphics.graphicsLayer
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicStudiesAppTheme

@Composable
fun MagicSubQuestCard(
    task: SubQuest,
    onToggleDone: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (task.isDone) {
        MaterialTheme.colorScheme.surface.copy(alpha = 0.3f)
    } else {
        when (task.priority) {
            Priority.URGENT -> MaterialTheme.colorScheme.error.copy(alpha = 0.15f)
            else -> MaterialTheme.colorScheme.surface
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(
                dimensionResource(R.dimen.border),
                MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f),
                MaterialTheme.shapes.medium
            ),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Row(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(dimensionResource(R.dimen.icon_size_medium))
                    .clip(CircleShape)
                    .background(
                        if (task.isDone) MaterialTheme.colorScheme.primary.copy(alpha = 0.6f) else Color.Transparent
                    )
                    .border(
                        dimensionResource(R.dimen.border),
                        MaterialTheme.colorScheme.outlineVariant,
                        CircleShape
                    )
                    .clickable(onClick = onToggleDone),
                contentAlignment = Alignment.Center
            ) {
                if (task.isDone) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        modifier = Modifier.size(dimensionResource(R.dimen.icon_size_small) * 0.9f),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_medium)))

            Column(modifier = Modifier.weight(1f)) {
                MagicText(
                    text = task.name,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        textDecoration = if (task.isDone) TextDecoration.LineThrough else TextDecoration.None
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.graphicsLayer {
                        if (task.isDone) alpha = 0.6f
                    }
                )
                
                Row(
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_tiny)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
                ) {
                    MagicPriorityBadge(priority = task.priority)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Schedule,
                            contentDescription = null,
                            modifier = Modifier.size(dimensionResource(R.dimen.icon_size_small) * 0.8f),
                            tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                        )
                        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_tiny)))
                        MagicText(
                            text = "${task.plannedTime}h",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                        )
                    }
                }
            }

            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MagicSubQuestCardPreview() {
    MagicStudiesAppTheme {
        MagicSubQuestCard(
            task = SubQuest(name = "Magic SubQuest", priority = Priority.URGENT, plannedTime = 2f, isDone = false),
            onToggleDone = {},
            onDelete = {}
        )
    }
}
