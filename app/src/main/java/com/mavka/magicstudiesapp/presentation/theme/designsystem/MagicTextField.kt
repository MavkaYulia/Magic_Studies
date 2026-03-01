package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
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
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isError: Boolean = false,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        isError = isError,
        placeholder = {
            Text(
                text = hintText,
                color = MagicMaterialColor.primary.copy(alpha = 0.4f)
            )
        },
        modifier = modifier
            .fillMaxWidth(),
        shape = MagicMaterialShapes.medium,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MagicMaterialColor.outline,
            unfocusedBorderColor = MagicMaterialColor.outlineVariant,
            focusedTextColor = MagicMaterialColor.primary,
            unfocusedTextColor = MagicMaterialColor.secondary,
            cursorColor = MagicMaterialColor.primary
        ),
        keyboardOptions = keyboardOptions,
        singleLine = true
    )
}


@Preview(showBackground = true)
@Composable
private fun MagicTextFieldPreview() {
    MagicTextField(
        "", {}, "Hint text"
    )
}