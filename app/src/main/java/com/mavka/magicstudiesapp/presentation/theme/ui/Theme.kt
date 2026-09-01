package com.mavka.magicstudiesapp.presentation.theme.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable


@Composable
fun MagicStudiesAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        MagicMaterialDarkColorScheme
    } else {
        MagicMaterialLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = MagicMaterialTypography,
        shapes = MagicMaterialShapes,
        content = content
    )
}
