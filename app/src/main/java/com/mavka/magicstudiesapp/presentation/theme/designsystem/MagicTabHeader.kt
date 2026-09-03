package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicStudiesAppTheme

@Composable
fun MagicTabHeader(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String
) {

    Column(
        modifier = modifier
            .wrapContentSize()
    ) {

        MagicTitle(title)

        Spacer(modifier = Modifier.height(4.dp))

        MagicText(
            text = subTitle,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onBackground.copy(
                    alpha = 0.6f
                )
            )
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun MagicTabHeaderPreview() {
    MagicStudiesAppTheme {
        MagicTabHeader(
            title = "Title",
            subTitle = "Subtitle"
        )
    }
}

