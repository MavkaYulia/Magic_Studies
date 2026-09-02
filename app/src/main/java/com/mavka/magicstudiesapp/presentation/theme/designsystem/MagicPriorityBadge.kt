package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mavka.magicstudiesapp.R
import com.mavka.magicstudiesapp.domain.models.Priority

import androidx.compose.foundation.layout.Column
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicStudiesAppTheme

@Composable
fun MagicPriorityBadge(priority: Priority, modifier: Modifier = Modifier) {

    val (color, icon, label) = when (priority) {
        Priority.URGENT -> Triple(
            MaterialTheme.colorScheme.error,
            Icons.Default.LocalFireDepartment,
            stringResource(R.string.urgent)
        )

        Priority.NORMAL -> Triple(
            MaterialTheme.colorScheme.outlineVariant,
            Icons.Default.Flag,
            stringResource(R.string.normal)
        )

        Priority.LOW -> Triple(
            MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
            Icons.Default.Remove,
            stringResource(R.string.low)
        )
    }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(dimensionResource(R.dimen.padding_small)))
            .background(color.copy(alpha = 0.2f))
            .padding(
                horizontal = dimensionResource(R.dimen.padding_small),
                vertical = dimensionResource(R.dimen.padding_tiny) / 2
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_tiny))
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(dimensionResource(R.dimen.icon_size_small) * 0.7f),
            tint = color
        )
        Text(
            text = label.uppercase(),
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
            color = color
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MagicPriorityBadgePreview() {
    MagicStudiesAppTheme {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            MagicPriorityBadge(priority = Priority.URGENT)
            MagicPriorityBadge(priority = Priority.NORMAL)
            MagicPriorityBadge(priority = Priority.LOW)
        }
    }
}

