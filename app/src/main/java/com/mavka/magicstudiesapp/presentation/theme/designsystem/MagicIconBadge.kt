package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Factory
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.mavka.magicstudiesapp.R
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicMaterialColor
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicMaterialShapes

@Composable
fun MagicIconBadge(icon: ImageVector) {
    Box(
        modifier = Modifier
            .size(dimensionResource(R.dimen.icon_size_large))
            .background(Color.Transparent, shape = MagicMaterialShapes.small),
        contentAlignment = Alignment.Center
    ) {
        Icon(imageVector = icon, contentDescription = "Icon", tint = MagicMaterialColor.primary)
    }
}

@Preview(showBackground = true)
@Composable
private fun MagicIconBadgePreview() {
    MagicIconBadge(
        Icons.Default.Factory
    )
}
