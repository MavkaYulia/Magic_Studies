package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.mavka.magicstudiesapp.R

import com.mavka.magicstudiesapp.presentation.theme.ui.MagicStudiesAppTheme

@Composable
fun MagicAddButtonExpanded(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add quest",
            modifier = Modifier.size(dimensionResource(R.dimen.icon_size_medium)),
            tint = contentColor
        )
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_medium)))
        Text(
            text = label,
            color = contentColor,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun MagicAddButtonIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.medium
            )
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add quest",
            modifier = Modifier.size(dimensionResource(R.dimen.icon_size_medium)),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MagicButtonsPreview() {
    MagicStudiesAppTheme {
        Column {
            MagicAddButtonExpanded("Magic Add Button", {})
            MagicAddButtonIcon({})
        }
    }
}
