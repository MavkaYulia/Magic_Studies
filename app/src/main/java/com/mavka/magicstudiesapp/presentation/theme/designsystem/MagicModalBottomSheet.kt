package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.TrendingDown
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material.icons.outlined.LocalFireDepartment
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mavka.magicstudiesapp.R
import com.mavka.magicstudiesapp.domain.models.Priority

@Composable
fun MagicModalBottomSheet(
    name: String,
    onNameChange: (String) -> Unit,
    priority: Priority,
    onPriorityChange: (Priority) -> Unit,
    hours: String,
    onHoursChange: (String) -> Unit,
    onAdd: () -> Unit,
    modifier: Modifier = Modifier
) {
    val initialTime = hours.toFloatOrNull() ?: 1f
    val initialHours = initialTime.toInt()
    val initialMinutes = ((initialTime - initialHours) * 60).toInt()

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        Text(
            text = stringResource(R.string.add_new_task).uppercase(),
            style = MaterialTheme.typography.labelMedium.copy(letterSpacing = 1.sp),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
        )

        MagicTextField(
            value = name,
            onValueChange = onNameChange,
            hintText = stringResource(R.string.task_hint)
        )

        Text(
            text = stringResource(R.string.priority).uppercase(),
            style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 1.sp),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_tiny))
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PriorityButton(
                icon = Icons.Outlined.LocalFireDepartment,
                label = stringResource(R.string.urgent),
                priority = Priority.URGENT,
                selected = priority == Priority.URGENT,
                onClick = { onPriorityChange(Priority.URGENT) },
                modifier = Modifier.weight(1f)
            )
            PriorityButton(
                icon = Icons.Outlined.Flag,
                label = stringResource(R.string.normal),
                priority = Priority.NORMAL,
                selected = priority == Priority.NORMAL,
                onClick = { onPriorityChange(Priority.NORMAL) },
                modifier = Modifier.weight(1f)
            )
            PriorityButton(
                icon = Icons.AutoMirrored.Outlined.TrendingDown,
                label = stringResource(R.string.low),
                priority = Priority.LOW,
                selected = priority == Priority.LOW,
                onClick = { onPriorityChange(Priority.LOW) },
                modifier = Modifier.weight(1f)
            )
        }

        Text(
            text = stringResource(R.string.time).uppercase(),
            style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 1.sp),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_tiny))
        )

        MagicTimeWheelPicker(
            initialHours = initialHours,
            initialMinutes = initialMinutes,
            onTimeSelected = { h, m ->
                val totalHours = h + (m / 60f)
                onHoursChange(totalHours.toString())
            },
            modifier = Modifier.fillMaxWidth()
        )

        MagicAddButtonExpanded(
            label = stringResource(R.string.add_to_quest_line),
            onClick = onAdd,
            containerColor = MaterialTheme.colorScheme.outlineVariant,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun PriorityButton(
    icon: ImageVector,
    label: String,
    priority: Priority,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedColor = when (priority) {
        Priority.URGENT -> MaterialTheme.colorScheme.error
        Priority.NORMAL -> MaterialTheme.colorScheme.outlineVariant
        Priority.LOW -> MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
    }

    Column(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .background(
                if (selected) selectedColor.copy(alpha = 0.2f)
                else Color.Transparent
            )
            .border(
                dimensionResource(R.dimen.border),
                if (selected) selectedColor
                else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f),
                MaterialTheme.shapes.medium
            )
            .clickable(onClick = onClick)
            .padding(vertical = dimensionResource(R.dimen.padding_small)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(dimensionResource(R.dimen.icon_size_small)),
            tint = if (selected) selectedColor else MaterialTheme.colorScheme.onBackground.copy(
                alpha = 0.6f
            )
        )
        Text(
            text = label.uppercase(),
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
            color = if (selected) selectedColor else MaterialTheme.colorScheme.onBackground.copy(
                alpha = 0.6f
            )
        )
    }
}
