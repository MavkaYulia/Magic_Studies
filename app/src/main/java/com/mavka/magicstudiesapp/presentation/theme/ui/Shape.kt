package com.mavka.magicstudiesapp.presentation.theme.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicShapes.extraLarge
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicShapes.extraSmall
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicShapes.large
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicShapes.medium
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicShapes.small

object MagicShapes {

    val extraSmall = RoundedCornerShape(2.dp)
    val small = RoundedCornerShape(4.dp)
    val medium = RoundedCornerShape(8.dp)
    val large = RoundedCornerShape(10.dp)
    val extraLarge = RoundedCornerShape(12.dp)

}

internal val MagicMaterialShapes = Shapes(
    extraSmall = extraSmall,
    small = small,
    medium = medium,
    large = large,
    extraLarge = extraLarge
)