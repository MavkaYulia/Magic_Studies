package com.mavka.magicstudiesapp.presentation.theme.ui

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

object MagicColor {
    val Parchment = Color(0xFFF5E6CA)
    val OldPaper = Color(0xFFECD2AA)
    val CandleLight = Color(0xFFFFE082)
    val WaxSealRed = Color(0xFF8C4A32)
    val IronInk = Color(0xFF3E2723)
    val ForestGreen = Color(0xFF2E4532)

    val DeepNight = Color(0xFF1B1811)
    val FadedGold = Color(0xFFC5A059)

    val UrgentRed = Color(0xFF7B1F16)
    val DarkerOldPaper = Color(0xFF2C261D)
}

internal val MagicMaterialLightColorScheme = lightColorScheme(
    primary = MagicColor.ForestGreen,
    onPrimary = MagicColor.Parchment,

    secondary = MagicColor.WaxSealRed,
    onSecondary = MagicColor.CandleLight,

    background = MagicColor.Parchment,
    onBackground = MagicColor.IronInk,

    tertiary = MagicColor.DeepNight,

    outline = MagicColor.ForestGreen,
    outlineVariant = MagicColor.FadedGold,

    surface = MagicColor.OldPaper,
    onSurface = MagicColor.IronInk,

    error = MagicColor.UrgentRed,
    onError = Color.White
)

internal val MagicMaterialDarkColorScheme = darkColorScheme(
    primary = MagicColor.WaxSealRed,
    onPrimary = MagicColor.CandleLight,

    secondary = MagicColor.ForestGreen,
    onSecondary = MagicColor.Parchment,

    background = MagicColor.DeepNight,
    onBackground = MagicColor.Parchment,

    tertiary = MagicColor.FadedGold,

    outline = MagicColor.FadedGold,
    outlineVariant = MagicColor.IronInk,

    surface = MagicColor.DarkerOldPaper,
    onSurface = MagicColor.Parchment,

    error = MagicColor.UrgentRed,
    onError = Color.White
)