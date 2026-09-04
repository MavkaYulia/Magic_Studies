package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mavka.magicstudiesapp.R
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicColor
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicStudiesAppTheme

fun ContentDrawScope.drawWithLayer(block: ContentDrawScope.() -> Unit) {
    with(drawContext.canvas.nativeCanvas) {
        val checkPoint = saveLayer(null, null)
        block()
        restoreToCount(checkPoint)
    }
}

data class QuestTab(
    val title: String,
    val icon: ImageVector
)

@Composable
fun MagicQuestTabSwitch(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    items: List<QuestTab>,
    onSelectionChange: (Int) -> Unit
) {
    if (items.isEmpty()) return

    val paddingSmall = dimensionResource(R.dimen.padding_small)
    val heightLarge = dimensionResource(R.dimen.height_large)
    val spacingSmall = dimensionResource(R.dimen.spacing_small)
    val elevation = dimensionResource(R.dimen.elevation)
    val shape = MaterialTheme.shapes.medium

    BoxWithConstraints(
        modifier = modifier
            .padding(paddingSmall)
            .height(heightLarge)
            .clip(shape)
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingSmall)
    ) {
        val tabWidth = maxWidth / items.size
        val indicatorOffset by animateDpAsState(
            targetValue = tabWidth * selectedIndex,
            animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing),
            label = "indicatorOffset"
        )

        Box(
            modifier = Modifier
                .offset(x = indicatorOffset)
                .shadow(elevation, shape)
                .width(tabWidth)
                .fillMaxHeight()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, MaterialTheme.colorScheme.primary, shape)
                .drawWithContent {
                    val paddingPx = paddingSmall.toPx()
                    val tabWidthPx = size.width / items.size
                    val cornerRadius = CornerRadius(8.dp.toPx())

                    drawRoundRect(
                        topLeft = Offset(indicatorOffset.toPx() + paddingPx, paddingPx),
                        size = Size(tabWidthPx - paddingPx * 2, size.height - paddingPx * 2),
                        color = MagicColor.ForestGreen,
                        cornerRadius = cornerRadius
                    )

                    drawWithLayer {
                        drawContent()
                        drawRoundRect(
                            topLeft = Offset(indicatorOffset.toPx(), 0f),
                            size = Size(tabWidthPx, size.height),
                            color = MagicColor.OldPaper,
                            cornerRadius = cornerRadius,
                            blendMode = BlendMode.SrcOut
                        )
                    }
                }
        ) {
            items.forEachIndexed { index, tab ->
                Row(
                    modifier = Modifier
                        .width(tabWidth)
                        .fillMaxHeight()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { onSelectionChange(index) },
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = tab.title,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(Modifier.width(spacingSmall))
                    MagicText(
                        text = tab.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MagicQuestTabSwitchPreview() {
    val tabs = listOf(
        QuestTab("Quests", Icons.Default.Book),
        QuestTab("Stats", Icons.Default.QueryStats)
    )
    MagicStudiesAppTheme {
        MagicQuestTabSwitch(
            modifier = Modifier.fillMaxWidth(),
            selectedIndex = 0,
            items = tabs,
            onSelectionChange = {}
        )
    }
}