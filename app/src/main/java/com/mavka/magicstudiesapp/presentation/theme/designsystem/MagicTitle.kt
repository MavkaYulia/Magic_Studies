package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import com.mavka.magicstudiesapp.presentation.theme.ui.MagicStudiesAppTheme

@Composable
fun MagicTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.displayLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun MagicSubTitle(
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = subtitle,
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
private fun MagicTitlePreview() {
    MagicStudiesAppTheme {
        Column {
            MagicTitle(title = "Magic Title")
            MagicSubTitle(subtitle = "Magic SubTitle")
        }
    }
}
