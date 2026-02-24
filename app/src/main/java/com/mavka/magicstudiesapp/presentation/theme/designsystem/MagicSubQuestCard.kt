package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mavka.magicstudiesapp.R
import com.mavka.magicstudiesapp.domain.models.SubQuest
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicMaterialColor
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicMaterialShapes
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicMaterialTypography

@Composable
fun MagicSubQuestCard(
    subQuest: SubQuest,
    onDeleteClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MagicMaterialColor.background, MagicMaterialShapes.large)
            .border(
                dimensionResource(R.dimen.border),
                MagicMaterialColor.secondary,
                MagicMaterialShapes.large
            )
            .padding(dimensionResource(R.dimen.padding_medium)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val checkMark by rememberLottieComposition(
            LottieCompositionSpec.RawRes(R.raw.check_mark)
        )

        val progress by animateLottieCompositionAsState(
            composition = checkMark,
            isPlaying = subQuest.isDone,
            restartOnPlay = false,
            iterations = 1
        )

        LottieAnimation(
            composition = checkMark,
            progress = { if (subQuest.isDone) progress else 0f },
            modifier = Modifier
                .size(dimensionResource(R.dimen.icon_size_medium))
        )

        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.spacing_small)))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = subQuest.name,
                color = MagicMaterialColor.primary,
                style = MagicMaterialTypography.bodyMedium,
                textDecoration = if (subQuest.isDone) TextDecoration.LineThrough else TextDecoration.None
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_tiny))
            ) {

                Text(
                    text = "hard",
                    color = MagicMaterialColor.error,    //todo()
                    style = MagicMaterialTypography.bodySmall
                )

                Spacer(modifier = Modifier.width(dimensionResource(R.dimen.spacing_medium)))

                Icon(
                    imageVector = Icons.Outlined.Timer,
                    contentDescription = null,
                    tint = MagicMaterialColor.primary.copy(alpha = 0.7f),
                    modifier = Modifier.size(14.dp)
                )

                Text(
                    text = "${subQuest.plannedTime}",
                    color = MagicMaterialColor.primary.copy(alpha = 0.7f),
                    style = MagicMaterialTypography.bodyMedium
                )
            }
        }

        IconButton(onClick = { onDeleteClick(subQuest.id) }) {
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = "Delete",
                tint = MagicMaterialColor.primary.copy(alpha = 0.8f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MagicSubQuestCardPreview() {
    MagicSubQuestCard(
        SubQuest(
            name = "subQuest 1",
            isDone = false,
            plannedTime = 3
        ),
        onDeleteClick = {}
    )
}
