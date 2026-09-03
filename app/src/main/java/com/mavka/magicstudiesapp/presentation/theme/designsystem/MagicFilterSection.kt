package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.mavka.magicstudiesapp.R
import com.mavka.magicstudiesapp.presentation.screens.quests.details.QuestFilter

import com.mavka.magicstudiesapp.presentation.theme.ui.MagicStudiesAppTheme
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MagicFilterSection(
    selectedFilter: QuestFilter,
    hideDone: Boolean,
    onFilterSelected: (QuestFilter) -> Unit,
    onHideDoneToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(R.dimen.padding_small)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        MagicText(
            text = stringResource(R.string.filter_label),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )

        FilterChip(
            label = stringResource(R.string.filter_all),
            selected = selectedFilter == QuestFilter.All,
            onClick = { onFilterSelected(QuestFilter.All) }
        )
        FilterChip(
            label = stringResource(R.string.urgent),
            selected = selectedFilter == QuestFilter.Urgent,
            onClick = { onFilterSelected(QuestFilter.Urgent) }
        )
        FilterChip(
            label = stringResource(R.string.normal),
            selected = selectedFilter == QuestFilter.Normal,
            onClick = { onFilterSelected(QuestFilter.Normal) }
        )
        FilterChip(
            label = stringResource(R.string.low),
            selected = selectedFilter == QuestFilter.Low,
            onClick = { onFilterSelected(QuestFilter.Low) }
        )
        FilterChip(
            label = stringResource(R.string.filter_hide_done),
            selected = hideDone,
            onClick = onHideDoneToggle
        )
    }
}

@Composable
private fun FilterChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(dimensionResource(R.dimen.padding_medium)))
            .background(
                if (selected) MaterialTheme.colorScheme.outlineVariant else Color.Transparent
            )
            .border(
                dimensionResource(R.dimen.border),
                if (selected) Color.Transparent else MaterialTheme.colorScheme.outlineVariant.copy(
                    alpha = 0.5f
                ),
                RoundedCornerShape(dimensionResource(R.dimen.padding_medium))
            )
            .clickable(onClick = onClick)
            .padding(
                horizontal = dimensionResource(R.dimen.margin_medium),
                vertical = dimensionResource(R.dimen.padding_tiny) * 1.5f
            ),
        contentAlignment = Alignment.Center
    ) {
        MagicText(
            text = label,
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
            color = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground.copy(
                alpha = 0.6f
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MagicFilterSectionPreview() {
    MagicStudiesAppTheme {
        MagicFilterSection(
            selectedFilter = QuestFilter.All,
            hideDone = false,
            onFilterSelected = {},
            onHideDoneToggle = {}
        )
    }
}

