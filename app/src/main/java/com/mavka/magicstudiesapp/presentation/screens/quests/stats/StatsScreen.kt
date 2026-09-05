package com.mavka.magicstudiesapp.presentation.screens.quests.stats

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adjust
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.RadioButtonChecked
import androidx.compose.material.icons.outlined.Timeline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mavka.magicstudiesapp.R
import com.mavka.magicstudiesapp.presentation.theme.dashedGridBackground
import com.mavka.magicstudiesapp.presentation.theme.designsystem.DonutSlice
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicCardData
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicChartContainer
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicDonutStats
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicFourGrid
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicPeriodToggle
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicText
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicTitle
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicStudiesAppTheme
import org.koin.androidx.compose.koinViewModel
import java.util.Locale

@Composable
fun StatsScreen(
    viewModel: StatsViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    StatsScreenContent(uiState = uiState)
}

@Composable
fun StatsScreenContent(
    uiState: StatsState
) {
    val scrollState = rememberScrollState()
    var selectedPeriod by remember { mutableIntStateOf(0) } // 0 for Week, 1 for Month

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
            .padding(dimensionResource(R.dimen.padding_large))
    ) {
        MagicTitle(title = stringResource(R.string.tab_stats))
        MagicText(text = stringResource(R.string.detailed_analytics_of_thy_scholarly_pursuits))

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_large)))

        MagicPeriodToggle(
            selectedIndex = selectedPeriod,
            onSelectionChange = { selectedPeriod = it }
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_large)))

        MagicFourGrid(
            items = listOf(
                MagicCardData(
                    icon = Icons.Outlined.AccessTime,
                    value = "${uiState.totalHours.toInt()}H",
                    label = "Total Hours"
                ),
                MagicCardData(
                    icon = Icons.Outlined.RadioButtonChecked,
                    value = "${uiState.totalSessions}",
                    label = "Sessions"
                ),
                MagicCardData(
                    icon = Icons.Outlined.Timeline,
                    value = if (uiState.totalSessions > 0) String.format(
                        Locale.getDefault(),
                        "%.1fH",
                        uiState.totalHours / uiState.totalSessions
                    ) else "0H",
                    label = "Avg Session"
                ),
                MagicCardData(
                    icon = Icons.Default.School,
                    value = String.format(
                        Locale.getDefault(),
                        "%.0f%%",
                        uiState.completionRate * 100
                    ),
                    label = "Task Rate"
                )
            )
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_large)))

        WeeklyStudyHoursChart()

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_large)))

        SubjectDistributionChart(uiState)

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_large)))

        LocationPerformanceChart()

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_large)))

        SkillProficiencyChart()

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_large)))

        LocationSummaryTable()

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_large)))
    }
}


@Composable
fun WeeklyStudyHoursChart() {
    // TODO fill real data
    MagicChartContainer(title = "WEEKLY STUDY HOURS", icon = Icons.Default.Timeline) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(top = 20.dp, bottom = 10.dp)
        ) {
            val heights = listOf(0.4f, 0.2f, 0.6f, 0.5f, 0.15f, 0.8f, 0.5f)
            val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .dashedGridBackground(
                        rows = 4,
                        columns = 7
                    )
            ) {
                Row(
                    modifier = Modifier.fillMaxSize()
                ) {
                    heights.forEach { h ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(30.dp)
                                    .fillMaxHeight(h)
                                    .background(
                                        color = MaterialTheme.colorScheme.primary,
                                        shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp)
                                    )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))


            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                days.forEach { day ->
                    Box(
                        modifier = Modifier.weight(1f), // Текст ідеально центрирується під своїм стовпчиком
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = day,
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SubjectDistributionChart(uiState: StatsState) {
    MagicChartContainer(
        title = stringResource(R.string.subject_distribution),
        icon = Icons.Default.Book
    ) {
        if (uiState.quests.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    stringResource(R.string.no_data_available),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            val slices = remember(uiState.quests) {
                uiState.quests.map { quest ->
                    DonutSlice(
                        label = quest.title,
                        value = quest.progress,
                        color = quest.color
                    )
                }
            }
            MagicDonutStats(
                slices = slices,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(R.dimen.padding_medium))
            )
        }
    }
}

@Composable
fun LocationPerformanceChart() {
    // TODO: Implement real Horizontal Bar Chart
    MagicChartContainer(title = "LOCATION PERFORMANCE", icon = Icons.Default.LocationOn) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val locations = listOf(
                "Scholar's Tavern" to 0.5f,
                "Grand Library" to 0.9f,
                "King's Garden" to 0.3f,
                "Wizard Tower" to 0.25f,
                "Scribe's Bookshop" to 0.45f
            )
            locations.forEach { (name, progress) ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = name,
                        modifier = Modifier.width(80.dp),
                        style = MaterialTheme.typography.labelSmall,
                        fontSize = 9.sp,
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(24.dp)
                            .background(MaterialTheme.colorScheme.surface)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(progress)
                                .fillMaxHeight()
                                .background(Color(0xFF8B2F31))
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SkillProficiencyChart() {
    // TODO: Implement real Radar Chart
    MagicChartContainer(title = "SKILL PROFICIENCY", icon = Icons.Default.Adjust) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Radar Chart Placeholder", color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
fun LocationSummaryTable() {
    // TODO: Move to designsystem if reused
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
        ),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
    ) {
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
            Text(
                text = "LOCATION SUMMARY",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_large)))

            // Table Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Haven",
                    modifier = Modifier.weight(2f),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Hours",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Avg Session",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Rating",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold
                )
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
            )

            val tableData = listOf(
                TableData("Scholar's Tavern", "63.5h", "1.5h", 4.7f),
                TableData("Grand Library", "120h", "1.54h", 4.9f),
                TableData("King's Garden", "30h", "1.2h", 4.3f),
                TableData("Wizard Tower", "22.5h", "1.5h", 4.5f),
                TableData("Scribe's Bookshop", "48h", "1.45h", 4.6f)
            )

            tableData.forEach { data ->
                TableRow(data)
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                )
            }
        }
    }
}

data class TableData(val haven: String, val hours: String, val avg: String, val rating: Float)

@Composable
fun TableRow(data: TableData) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(data.haven, modifier = Modifier.weight(2f), style = MaterialTheme.typography.bodySmall)
        Text(data.hours, modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodySmall)
        Text(data.avg, modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodySmall)
        Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Default.Star,
                contentDescription = null,
                tint = Color(0xFFC5A046),
                modifier = Modifier.size(12.dp)
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(data.rating.toString(), style = MaterialTheme.typography.bodySmall)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun StatsScreenPreview() {
    MagicStudiesAppTheme {
        StatsScreenContent(uiState = StatsState())
    }
}
