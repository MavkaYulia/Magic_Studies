package com.mavka.magicstudiesapp.domain.provider

import com.mavka.magicstudiesapp.domain.metrics.PathsMetrics
import com.mavka.magicstudiesapp.domain.models.PathModel
import com.mavka.magicstudiesapp.domain.repository.PathRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class PathOverview(
    val paths: List<PathModel>,
    val metrics: PathsMetrics
)

class PathOverviewProvider(
    pathRepository: PathRepository,
    externalScope: CoroutineScope
) {
    val overview: StateFlow<PathOverview> = pathRepository.getPaths()
        .map { paths ->
            PathOverview(
                paths = paths,
                metrics = calculateMetrics(paths)
            )
        }
        .flowOn(Dispatchers.Default)
        .stateIn(
            scope = externalScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PathOverview(
                paths = emptyList(),
                metrics = PathsMetrics(0, 0, 0, 0, 0, 0, 0.0)
            )
        )

    private fun calculateMetrics(paths: List<PathModel>): PathsMetrics {
        val completedQuests = paths
            .asSequence()
            .flatMap { it.quests.asSequence() }
            .filter { it.isDone }
            .toList()

        val totalQuestsCount = paths.sumOf { it.quests.size }
        val completedQuestsCount = completedQuests.size

        return PathsMetrics(
            completedQuestsCount = completedQuestsCount,
            completedPlannedTimeMinutes = completedQuests.sumOf { it.plannedTime },
            totalPlannedTimeMinutes = paths.sumOf { it.totalPlannedTimeMinutes },
            totalPathsCount = paths.size,
            totalQuestsCount = totalQuestsCount,
            remainingQuestsCount = totalQuestsCount - completedQuestsCount,
            estimationAccuracyPercentage = 0.0
        )
    }
}
