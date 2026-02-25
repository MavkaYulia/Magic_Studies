package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.mavka.magicstudiesapp.R
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicMaterialColor
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicMaterialShapes
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicMaterialTypography

@Composable
fun MagicTimePicker(
    onConfirmClick: (Int) -> Unit,
    onDismissClick: () -> Unit
) {
    var selectedMinutes by remember { mutableIntStateOf(0) }
    var isCustomMode by remember { mutableStateOf(false) }

    var hoursInput by remember { mutableStateOf("") }
    var minutesInput by remember { mutableStateOf("") }

    val timePresets = listOf(
        5, 10, 15, 30, 45, 60, 120, 180
    )

    AlertDialog(
        containerColor = MagicMaterialColor.surface,
        onDismissRequest = onDismissClick,
        confirmButton = {
            TextButton(
                onClick = {
                    if (isCustomMode) {
                        val hours = hoursInput.toIntOrNull() ?: 0
                        val minutes = minutesInput.toIntOrNull() ?: 0
                        selectedMinutes = (hours * 60) + minutes
                    }
                    onConfirmClick(selectedMinutes)
                }
            ) {
                MagicText(
                    modifier = Modifier.wrapContentSize(),
                    text = stringResource(R.string.dialog_confirm)
                )
            }
        },
        shape = MagicMaterialShapes.large,
        dismissButton = {
            TextButton(onClick = onDismissClick) {
                MagicText(
                    modifier = Modifier.wrapContentSize(),
                    text = stringResource(R.string.dialog_cancel)
                )
            }
        },
        title = {
            MagicTitle(stringResource(R.string.duration_dialog_title))
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_medium))) {

                if (!isCustomMode) {
                    DurationSelector(
                        presets = timePresets,
                        selectedMinutes = selectedMinutes,
                        onSelect = { selectedMinutes = it },
                        onCustomClick = { isCustomMode = true }
                    )
                } else {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_small))
                    ) {
                        MagicTextField(
                            value = hoursInput,
                            onValueChange = { newValue ->
                                if (newValue.all { it.isDigit() }) {
                                    hoursInput = newValue
                                }
                            },
                            hintText = stringResource(R.string.label_hours),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                            modifier = Modifier.weight(1f)
                        )

                        MagicTextField(
                            value = minutesInput,
                            onValueChange = { newValue ->
                                if (newValue.all { it.isDigit() }) {
                                    minutesInput = newValue
                                }
                            },
                            hintText = stringResource(R.string.label_minutes),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                            modifier = Modifier.weight(1f)
                        )
                    }

                    TextButton(
                        onClick = { isCustomMode = false }
                    ) {
                        MagicText(text = stringResource(R.string.back_to_presets))
                    }
                }
            }
        }
    )
}

@Composable
fun DurationSelector(
    presets: List<Int>,
    selectedMinutes: Int,
    onSelect: (Int) -> Unit,
    onCustomClick: () -> Unit
) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_small))) {
        presets.forEach { minutes ->

            val label = if (minutes < 60)
                stringResource(R.string.duration_minutes, minutes)
            else
                stringResource(R.string.duration_hours, minutes / 60)

            FilterChip(
                selected = selectedMinutes == minutes,
                onClick = { onSelect(minutes) },
                label = { MagicText(text = label, style = MagicMaterialTypography.bodySmall) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MagicMaterialColor.secondary.copy(0.8f),
                    selectedLabelColor = MagicMaterialColor.onSecondary
                )
            )
        }

        AssistChip(
            onClick = onCustomClick,
            label = { MagicText(text = stringResource(R.string.duration_custom)) }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MagicTimePickerPreview() {
    MagicTimePicker(
        onConfirmClick = {},
        onDismissClick = {}
    )
}
