package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mavka.magicstudiesapp.R
import kotlin.math.abs

@Composable
fun MagicTimeWheelPicker(
    modifier: Modifier = Modifier,
    initialHours: Int = 0,
    initialMinutes: Int = 0,
    onTimeSelected: (hours: Int, minutes: Int) -> Unit,
    visibleItemsCount: Int = 3,
    itemHeight: Dp = 36.dp
) {
    val hours = remember { (0..23).map { it.toString().padStart(2, '0') } }
    val minutes = remember { (0..55 step 5).map { it.toString().padStart(2, '0') } }

    var selectedHourIndex by remember { mutableIntStateOf(initialHours.coerceIn(0, 23)) }
    var selectedMinuteIndex by remember {
        mutableIntStateOf((initialMinutes / 5).coerceIn(0, minutes.size - 1))
    }

    LaunchedEffect(selectedHourIndex, selectedMinuteIndex) {
        onTimeSelected(selectedHourIndex, selectedMinuteIndex * 5)
    }

    Box(
        modifier = modifier
            .height(itemHeight * visibleItemsCount)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.1f))
            .border(
                dimensionResource(R.dimen.border),
                MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f),
                MaterialTheme.shapes.medium
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(itemHeight)
                .background(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.2f))
        )

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            MagicWheelColumn(
                items = hours,
                initialIndex = selectedHourIndex,
                visibleItemsCount = visibleItemsCount,
                itemHeight = itemHeight,
                onItemSelected = { selectedHourIndex = it },
                modifier = Modifier.weight(1f)
            )

            Text(
                text = ":",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            MagicWheelColumn(
                items = minutes,
                initialIndex = selectedMinuteIndex,
                visibleItemsCount = visibleItemsCount,
                itemHeight = itemHeight,
                onItemSelected = { selectedMinuteIndex = it },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MagicWheelColumn(
    items: List<String>,
    initialIndex: Int,
    visibleItemsCount: Int,
    itemHeight: Dp,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = initialIndex)
    val snapFlingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    val paddingItemsCount = visibleItemsCount / 2

    val focusedIndex by remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val visibleItems = layoutInfo.visibleItemsInfo
            if (visibleItems.isEmpty()) return@derivedStateOf initialIndex

            val viewportCenter = (layoutInfo.viewportStartOffset + layoutInfo.viewportEndOffset) / 2
            visibleItems.minByOrNull {
                abs((it.offset + it.size / 2) - viewportCenter)
            }?.index?.minus(paddingItemsCount) ?: initialIndex
        }
    }

    LaunchedEffect(focusedIndex) {
        if (focusedIndex in items.indices) {
            onItemSelected(focusedIndex)
        }
    }

    LazyColumn(
        state = listState,
        flingBehavior = snapFlingBehavior,
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(paddingItemsCount) {
            Spacer(modifier = Modifier.height(itemHeight))
        }

        items(items.size) { index ->
            val distance = abs(focusedIndex - index)
            val alpha = when (distance) {
                0 -> 1.0f
                1 -> 0.4f
                else -> 0.2f
            }

            Box(
                modifier = Modifier
                    .height(itemHeight)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = items[index],
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = if (distance == 0) FontWeight.Bold else FontWeight.Normal,
                        fontSize = if (distance == 0) 18.sp else 16.sp
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.alpha(alpha)
                )
            }
        }

        items(paddingItemsCount) {
            Spacer(modifier = Modifier.height(itemHeight))
        }
    }
}
