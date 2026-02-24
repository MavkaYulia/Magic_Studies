package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mavka.magicstudiesapp.R
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicMaterialColor
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicMaterialShapes

@Composable
fun MagicProgressBar(
    modifier: Modifier = Modifier,
    progress: Float
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.height_small))
            .border(
                0.3.dp,
                MagicMaterialColor.outline,
                MagicMaterialShapes.small
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress.coerceIn(0f, 1f))
                .fillMaxHeight()
                .background(MagicMaterialColor.secondary, MagicMaterialShapes.small)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MagicProgressBarPreview() {
    MagicProgressBar(
        progress = 0.5f
    )
}
