package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.atan2

data class DonutSlice(
    val label: String,
    val value: Float,
    val color: Color
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MagicDonutStats(
    slices: List<DonutSlice>,
    modifier: Modifier = Modifier,
    chartSize: Dp = 200.dp,
    strokeWidth: Dp = 42.dp,
    gapAngle: Float = 3f
) {
    val totalValue = slices.sumOf { it.value.toDouble() }.toFloat()
    val baseStartAngle = -80f

    // Стан для анімації появи (від 0 до 1)
    val appearanceProgress = remember { Animatable(0f) }

    // Стан для збереження індексу обраного (клікнутого) сегмента
    var selectedIndex by remember { mutableStateOf<Int?>(null) }

    // Запускаємо анімацію при першій появі компонента
    LaunchedEffect(Unit) {
        appearanceProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1200, easing = FastOutSlowInEasing)
        )
    }

    // Рахуємо анімовану товщину для кожного сегмента
    val animatedStrokeWidths = slices.mapIndexed { index, _ ->
        val isSelected = index == selectedIndex
        animateFloatAsState(
            targetValue = if (isSelected) strokeWidth.value * 1.3f else strokeWidth.value,
            label = "stroke_anim_$index"
        ).value
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Canvas(
            modifier = Modifier
                .size(chartSize)
                // Даємо більший padding, щоб при збільшенні дуга не обрізалася
                .padding(strokeWidth * 1.3f / 2)
                .pointerInput(Unit) {
                    detectTapGestures { tapOffset ->
                        // Знаходимо центр Canvas
                        val center = Offset(size.width / 2f, size.height / 2f)
                        val dx = tapOffset.x - center.x
                        val dy = tapOffset.y - center.y

                        // Рахуємо кут кліку від 0 до 360 градусів (0 - це 3 години на циферблаті)
                        var tapAngle = Math.toDegrees(atan2(dy.toDouble(), dx.toDouble())).toFloat()
                        if (tapAngle < 0) tapAngle += 360f

                        // Нормалізуємо стартовий кут графіка у формат 0..360
                        val normalizedStart = (baseStartAngle % 360f + 360f) % 360f

                        // Знаходимо кут кліку ВІДНОСНО початку нашого графіка
                        var relativeAngle = tapAngle - normalizedStart
                        if (relativeAngle < 0) relativeAngle += 360f

                        // Шукаємо, в який саме сегмент ми потрапили
                        var currentA = 0f
                        var clickedIndex: Int? = null
                        for (i in slices.indices) {
                            val sweepA = (slices[i].value / totalValue) * 360f
                            if (relativeAngle in currentA..(currentA + sweepA)) {
                                clickedIndex = i
                                break
                            }
                            currentA += sweepA
                        }

                        // Якщо клікнули по вже обраному – знімаємо виділення
                        selectedIndex = if (selectedIndex == clickedIndex) null else clickedIndex
                    }
                }
        ) {

            // Малюємо сегменти (спочатку всі неактивні, щоб обраний малювався поверх них)
            val slicesToDraw = slices.indices.sortedBy { it == selectedIndex }

            for (index in slicesToDraw) {
                val slice = slices[index]

                // Вираховуємо початковий кут саме для цього сегмента
                // (Щоб кожен сегмент малювався на своєму місці)
                var sliceStartAngle = baseStartAngle
                for (i in 0 until index) {
                    sliceStartAngle += (slices[i].value / totalValue) * 360f
                }

                val fullSweep = (slice.value / totalValue) * 360f

                // Застосовуємо анімацію появи до кута заповнення
                val animatedSweep = fullSweep * appearanceProgress.value
                val effectiveSweep = (animatedSweep - gapAngle).coerceAtLeast(0f)

                // Малюємо, якщо ефективний кут більше нуля
                if (effectiveSweep > 0f) {
                    drawArc(
                        color = slice.color,
                        startAngle = sliceStartAngle + (gapAngle / 2f),
                        sweepAngle = effectiveSweep,
                        useCenter = false,
                        style = Stroke(width = animatedStrokeWidths[index].dp.toPx())
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Легенда
        FlowRow(
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.Center,
            maxItemsInEachRow = 4,
            modifier = Modifier.fillMaxWidth()
        ) {
            slices.forEachIndexed { index, slice ->
                val isSelected = index == selectedIndex
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            // Якщо сегмент обрано, маркер в легенді теж трохи збільшуємо
                            .size(if (isSelected) 16.dp else 12.dp)
                            .clip(CircleShape)
                            .background(slice.color)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = slice.label,
                        // Можна також виділяти текст, роблячи його жирнішим чи темнішим
                        color = if (isSelected) Color.Black else Color(0xFF5A4A3A),
                        fontSize = 14.sp,
                        fontFamily = FontFamily.Serif
                    )
                }
            }
        }
    }
}