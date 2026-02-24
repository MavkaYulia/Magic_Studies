package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicMaterialColor
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicMaterialShapes


@Composable
fun MagicTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hintText: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = hintText,
                color = MagicMaterialColor.primary.copy(alpha = 0.4f)
            )
        },
        modifier = modifier
            .fillMaxWidth(),
        shape = MagicMaterialShapes.large,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MagicMaterialColor.outline,
            unfocusedBorderColor = MagicMaterialColor.outlineVariant,
            focusedTextColor = MagicMaterialColor.primary,
            unfocusedTextColor = MagicMaterialColor.secondary,
            cursorColor = MagicMaterialColor.primary
        ),
        singleLine = true
    )
}


@Preview(showBackground = true)
@Composable
private fun MagicTextFieldPreview() {
    return MagicTextField(
        "", {}, "Hint text"
    )
}