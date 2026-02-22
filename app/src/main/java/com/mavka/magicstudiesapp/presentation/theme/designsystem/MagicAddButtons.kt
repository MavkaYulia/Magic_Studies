package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.mavka.magicstudiesapp.R
import com.mavka.magicstudiesapp.presentation.theme.ui.Magic
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicMaterialTypography

@Composable
fun MagicAddButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = Magic.shape.button,
        colors = ButtonDefaults.buttonColors(
            containerColor = Magic.colors.ForestGreen
        )
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add quest",
            modifier = Modifier.size(dimensionResource(R.dimen.button_icon_size)),
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.standard_padding)))
        Text(text = text, style = MagicMaterialTypography.labelLarge)
    }
}

@Preview(showBackground = true)
@Composable
private fun MagicButtonsPreview() {
    MagicAddButton("MagicAddButton", {})
}
