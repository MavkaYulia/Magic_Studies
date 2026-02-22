package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.mavka.magicstudiesapp.presentation.theme.ui.Magic
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicMaterialTypography

@Composable
fun MagicText(
    title: String,
    style: TextStyle = MagicMaterialTypography.bodyLarge
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            style = style,
            color = Magic.colors.ForestGreen
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MagicTextPreview() {
    return MagicText(title = "MagicText")
}
