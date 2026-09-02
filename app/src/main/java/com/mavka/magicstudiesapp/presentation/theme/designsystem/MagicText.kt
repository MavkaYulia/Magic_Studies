package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview

import com.mavka.magicstudiesapp.presentation.theme.ui.MagicStudiesAppTheme

@Composable
fun MagicText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    color: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.onSurface
) {
    Text(
        text = text,
        style = style,
        color = color,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun MagicTextPreview() {
    MagicStudiesAppTheme {
        MagicText(text = "MagicText")
    }
}
