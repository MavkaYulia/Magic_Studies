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

data class QuestOverview(
    val quests: List<PathModel>,
    val metrics: QuestsMetrics
)

class QuestOverviewProvider(
    questRepository: QuestRepository,
    externalScope: CoroutineScope
) {
    val overview: StateFlow<QuestOverview> = questRepository.getQuests()
        .map { quests ->
            QuestOverview(
                quests = quests,
                metrics = calculateMetrics(quests)
            )
        }
        .flowOn(Dispatchers.Default)
        .stateIn(
            scope = externalScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = QuestOverview(
                quests = emptyList(),
                metrics = QuestsMetrics(0, 0, 0, 0, 0, 0, 0.0)
            )
        )

    private fun calculateMetrics(quests: List<PathModel>): QuestsMetrics {
        val completedSubQuests = quests
            .asSequence()
            .flatMap { it.subQuests.asSequence() }
            .filter { it.isDone }
            .toList()

        val totalSubQuestsCount = quests.sumOf { it.subQuests.size }
        val completedSubQuestsCount = completedSubQuests.size

        return QuestsMetrics(
            completedSubquestsCount = completedSubQuestsCount,
            completedPlannedTimeMinutes = completedSubQuests.sumOf { it.plannedTime },
            totalPlannedTimeMinutes = quests.sumOf { it.totalPlannedTimeMinutes },
            totalQuestsCount = quests.size,
            totalSubQuestsCount = totalSubQuestsCount,
            remainingQuestsCount = totalSubQuestsCount - completedSubQuestsCount,
            estimationAccuracyPercentage = 0.0
        )
    }
}
