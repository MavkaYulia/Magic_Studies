package com.mavka.magicstudiesapp.presentation.theme.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun MagicStudiesAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = MagicMaterialColor,
        typography = MagicMaterialTypography,
        shapes = MagicMaterialShapes,
        content = content
    )
}
