package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.mavka.magicstudiesapp.R
import com.mavka.magicstudiesapp.presentation.theme.ui.Magic
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicMaterialTypography

@Composable
fun MagicTitle(title: String) {
    Box(
        modifier = Modifier
            .padding(
                dimensionResource(id = R.dimen.standard_margin)
            )
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MagicMaterialTypography.titleLarge,
            color = Magic.colors.ForestGreen
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MagicTitlePreview() {
    return MagicTitle(title = "MagicTitle")
}
