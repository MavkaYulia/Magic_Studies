package com.mavka.magicstudiesapp.presentation.theme.ui

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mavka.magicstudiesapp.R

object MagicTypography {
    val MedievalFontFamily = FontFamily(
        Font(R.font.magic_regular, FontWeight.Normal),
        Font(R.font.magic_light, FontWeight.Light),
        Font(R.font.magic_bold, FontWeight.Bold),
        Font(R.font.magic_semi_bold, FontWeight.SemiBold),
        Font(R.font.magic_medium, FontWeight.Medium)
    )
}

internal val MagicMaterialTypography = Typography(

    displayLarge = TextStyle(
        fontFamily = Magic.typography.MedievalFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = Magic.typography.MedievalFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Magic.typography.MedievalFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Magic.typography.MedievalFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Magic.typography.MedievalFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    labelLarge = TextStyle(
        fontFamily = Magic.typography.MedievalFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    )
)