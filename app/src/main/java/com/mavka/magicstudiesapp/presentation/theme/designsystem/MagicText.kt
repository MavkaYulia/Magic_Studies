package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicMaterialColor
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicMaterialTypography

@Composable
fun MagicText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = MagicMaterialTypography.bodyLarge
    ) {
    Box(
        modifier = modifier
    ) {
        Text(
            text = text,
            style = style,
            color = MagicMaterialColor.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MagicTextPreview() {
    return MagicText(text = "MagicText")
}
