package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mavka.magicstudiesapp.R
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicStudiesAppTheme

@Composable
fun MagicTopAppBar(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.app_name),
    onBackClick: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .height(dimensionResource(id = R.dimen.height_large))
            .padding(horizontal = dimensionResource(id = R.dimen.padding_medium)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(),
                    onClick = onBackClick
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.back),
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_medium)))

            MagicText(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
        }

        actions()
    }
}

@Preview(showBackground = true)
@Composable
fun MagicTopAppBarPreview() {
    MagicStudiesAppTheme {
        MagicTopAppBar(
            title = "Magic",
            onBackClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MagicTopAppBarWithActionPreview() {
    MagicStudiesAppTheme {
        MagicTopAppBar(
            title = "Magic",
            onBackClick = {},
            actions = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        )
    }
}
