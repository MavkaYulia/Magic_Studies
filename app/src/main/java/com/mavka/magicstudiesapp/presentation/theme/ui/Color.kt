package com.mavka.magicstudiesapp.presentation.theme.ui

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

object MagicColor {
    val Parchment = Color(0xFFF5E6CA)
    val OldPaper = Color(0xFFE8D4B4)
    val CandleLight = Color(0xFFFFE082)

    val WaxSealRed = Color(0xFF8C4A32)
    val IronInk = Color(0xFF3E2723)
    val ForestGreen = Color(0xFF2E4532)

    val DeepNight = Color(0xFF1B1811)
    val FadedGold = Color(0xFFC5A059)
}

internal val MagicColorScheme = lightColorScheme(
    primary = MagicColor.WaxSealRed,
    onPrimary = Color.White,

    secondary = MagicColor.ForestGreen,
    onSecondary = Color.White,

    background = MagicColor.Parchment,
    onBackground = MagicColor.IronInk,

    surface = MagicColor.OldPaper,
    onSurface = MagicColor.IronInk,

    error = Color(0xFF7B1F16),
    onError = Color.White
)