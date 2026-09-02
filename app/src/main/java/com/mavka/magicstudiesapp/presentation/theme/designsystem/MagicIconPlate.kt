package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Science
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.dimensionResource
import com.mavka.magicstudiesapp.R
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicStudiesAppTheme

@Composable
fun MagicIconPlate(
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(dimensionResource(R.dimen.icon_size_large) * 1.6f)
            .background(
                MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
                RoundedCornerShape(dimensionResource(R.dimen.padding_medium))
            )
            .border(
                dimensionResource(R.dimen.border),
                MaterialTheme.colorScheme.outlineVariant,
                RoundedCornerShape(dimensionResource(R.dimen.padding_medium))
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(dimensionResource(R.dimen.icon_size_large)),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MagicIconPlatePreview() {
    MagicStudiesAppTheme {
        MagicIconPlate(
            Icons.Default.Science
        )
    }
}



