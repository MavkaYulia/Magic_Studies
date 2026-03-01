package com.mavka.magicstudiesapp.presentation.theme.ui

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

object MagicColor {
    val ForestGreen = Color(0xFF2E4532)
    val White = Color(0xFFFFFFFF)

    val SoftGold = Color(0xFFC2B280)
    val DarkInk = Color(0xFF130E0A)
    val Parchment = Color(0xFFFCF5E5)

    val CopperRose = Color(0xFF946969)

    val DarkWood = Color(0xFF7D5B3A)
    val OldIron = Color(0xFFBBB6B5)

    val OldPaper = Color(0xFFF0E4D1)
    val OldCard = Color(0xFF18161B)

    val RedLead = Color(0xFFBF3B26)
}

internal val MagicMaterialColor = lightColorScheme(
    primary = MagicColor.ForestGreen,
    onPrimary = MagicColor.White,

    secondary = MagicColor.SoftGold,
    onSecondary = MagicColor.DarkInk,

    background = MagicColor.Parchment,
    onBackground = MagicColor.DarkInk,

    tertiary = MagicColor.CopperRose,

    outline = MagicColor.DarkWood,
    outlineVariant = MagicColor.OldIron,

    surface = MagicColor.OldPaper,
    onSurface = MagicColor.OldCard,

    error = MagicColor.RedLead,
    onError = MagicColor.White
)