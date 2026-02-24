package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicMaterialColor
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicMaterialTypography

@Composable
fun MagicTitle(title: String) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MagicMaterialTypography.displayLarge,
            color = MagicMaterialColor.primary
        )
    }
}

@Composable
fun MagicSubTitle(subtitle: String) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = subtitle,
            style = MagicMaterialTypography.headlineMedium,
            color = MagicMaterialColor.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MagicTitlePreview() {
    Column {
        MagicTitle(title = "Magic Title")
        MagicSubTitle(subtitle = "Magic SubTitle")
    }
}
