package com.mavka.magicstudiesapp.presentation.theme.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable


private val DarkColorScheme = darkColorScheme(
    primary = MagicColor.WaxSealRed,
    secondary = MagicColor.ForestGreen,
    tertiary = MagicColor.FadedGold
)

/*
private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
*/

@Composable
fun MagicStudiesAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    /*  val colorScheme = when {
          dynamicColor -> {
              val context = LocalContext.current
              if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
          }

          darkTheme -> DarkColorScheme
          else -> LightColorScheme
      }*/

    MaterialTheme(
        colorScheme = MagicMaterialColor,
        typography = MagicMaterialTypography,
        shapes = MagicMaterialShapes,
        content = content
    )
}
