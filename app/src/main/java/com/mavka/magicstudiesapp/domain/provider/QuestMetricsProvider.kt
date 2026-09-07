package com.mavka.magicstudiesapp.domain.provider


import com.mavka.magicstudiesapp.domain.metrics.QuestsMetrics
import com.mavka.magicstudiesapp.domain.models.PathModel
import com.mavka.magicstudiesapp.domain.repository.QuestRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class QuestMetricsProvider(
    questRepository: QuestRepository,
    externalScope: CoroutineScope
) {
    val questMetrics: StateFlow<QuestsMetrics> = questRepository.getQuests()
        .map { quests -> calculateMetrics(quests) }
        .flowOn(Dispatchers.Default)
        .stateIn(
            scope = externalScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = QuestsMetrics(0, 0, 0, 0,  0,0, 0.0)
        )

    private fun calculateMetrics(quests: List<PathModel>): QuestsMetrics {
        val completedSubQuests = quests
            .flatMap { it.subQuests }
            .filter { it.isDone }

        val sumAllQuests = quests.sumOf { it.subQuests.size }
        val completedSubQuestsSize = completedSubQuests.size

        return QuestsMetrics(
            completedSubquestsCount = completedSubQuestsSize,
            completedPlannedTimeMinutes = completedSubQuests.sumOf { it.plannedTime },
            totalPlannedTimeMinutes = quests.sumOf { it.totalPlannedTimeMinutes },
            totalQuestsCount = quests.size,
            totalSubQuestsCount = sumAllQuests,
            remainingQuestsCount = sumAllQuests - completedSubQuestsSize,
            estimationAccuracyPercentage = 0.0
        )
    }
}
