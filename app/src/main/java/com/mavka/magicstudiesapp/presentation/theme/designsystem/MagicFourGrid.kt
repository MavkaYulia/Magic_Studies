package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.mavka.magicstudiesapp.R

data class MagicCardData(
    val icon: ImageVector,
    val value: String,
    val label: String
)

@Composable
fun MagicFourGrid(
    items: List<MagicCardData>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_medium))
    ) {
        // First Row
        Row(horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_medium))) {
            items.getOrNull(0)?.let { data ->
                StatCard(
                    icon = data.icon,
                    value = data.value,
                    label = data.label,
                    modifier = Modifier.weight(1f)
                )
            }
            items.getOrNull(1)?.let { data ->
                StatCard(
                    icon = data.icon,
                    value = data.value,
                    label = data.label,
                    modifier = Modifier.weight(1f)
                )
            }
        }
        // Second Row
        Row(horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_medium))) {
            items.getOrNull(2)?.let { data ->
                StatCard(
                    icon = data.icon,
                    value = data.value,
                    label = data.label,
                    modifier = Modifier.weight(1f)
                )
            }
            items.getOrNull(3)?.let { data ->
                StatCard(
                    icon = data.icon,
                    value = data.value,
                    label = data.label,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun StatCard(
    icon: ImageVector,
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        ),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_medium)))
            Text(
                text = value,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}
