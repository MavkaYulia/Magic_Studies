package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.mavka.magicstudiesapp.R
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicMaterialColor
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicMaterialShapes
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicMaterialTypography

@Composable
fun MagicAddDialog(
    onDismiss: () -> Unit,
    onCreate: (String) -> Unit
) {
    var subjectName by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = MagicMaterialShapes.extraLarge,
            colors = CardDefaults.cardColors(containerColor = MagicMaterialColor.surface),
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium))
        ) {
            Column(
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_large))
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.dialog_title_add_subject),
                        color = MagicMaterialColor.primary,
                        style = MagicMaterialTypography.titleLarge
                    )
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.size(dimensionResource(R.dimen.icon_size_large))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = MagicMaterialColor.primary.copy(alpha = 0.7f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_large)))

                Text(
                    text = stringResource(R.string.dialog_subtitle_add_subject),
                    color = MagicMaterialColor.primary,
                    style = MagicMaterialTypography.titleMedium
                )

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_small)))

                MagicTextField(value = subjectName,
                    { subjectName = it },
                    stringResource(R.string.dialog_hint_add_subject))

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_extra_large)))

                Button(
                    onClick = { onCreate(subjectName) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = MagicMaterialShapes.large,
                    colors = ButtonDefaults.buttonColors(containerColor = MagicMaterialColor.primary)
                ) {
                    Text(
                        text = stringResource(R.string.create_new_quest),
                        color = MagicMaterialColor.background,
                        style = MagicMaterialTypography.titleMedium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MagicAddDialogPreview() {
    MagicAddDialog(
        {}, {}
    )
}