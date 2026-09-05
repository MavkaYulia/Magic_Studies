package com.mavka.magicstudiesapp.presentation.theme

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.dashedGridBackground(
    color: Color = Color(0xFFC2B280).copy(alpha = 0.4f), // колір пунктиру під твій дизайн
    strokeWidth: Dp = 1.dp,
    dashLength: Float = 8f,
    gapLength: Float = 8f,
    rows: Int = 4,      // кількість горизонтальних секцій (ліній буде rows + 1)
    columns: Int = 7   // кількість вертикальних секцій
) = drawBehind {
    val strokePx = strokeWidth.toPx()
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashLength, gapLength), 0f)

    // Горизонтальні пунктирні лінії
    val rowHeight = size.height / rows
    for (i in 0..rows) {
        val y = i * rowHeight
        drawLine(
            color = color,
            start = Offset(0f, y),
            end = Offset(size.width, y),
            strokeWidth = strokePx,
            pathEffect = pathEffect
        )
    }

    // Вертикальні пунктирні лінії
    val colWidth = size.width / columns
    for (i in 0..columns) {
        val x = i * colWidth
        drawLine(
            color = color,
            start = Offset(x, 0f),
            end = Offset(x, size.height),
            strokeWidth = strokePx,
            pathEffect = pathEffect
        )
    }
}