package com.mavka.magicstudiesapp.presentation.screens.quests.stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mavka.magicstudiesapp.domain.models.PathModel
import com.mavka.magicstudiesapp.domain.provider.QuestMetricsProvider
import com.mavka.magicstudiesapp.domain.repository.QuestRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

data class StatsUiState(
    val completedSubquestsCount: Int = 0,
    val completedPlannedTimeMinutes: Int = 0,
    val totalPlannedTimeMinutes: Int = 0,
    val totalQuestsCount: Int = 0,
    val totalSubQuestsCount: Int = 0,
    val remainingQuestsCount: Int = 0,
    val estimationAccuracyPercentage: Double = 0.0,

    val quests: List<PathModel> = emptyList(),

    val isLoading: Boolean = true
)
class StatsViewModel(
    private val questRepository: QuestRepository,
    private val questMetricsProvider: QuestMetricsProvider,
) : ViewModel() {

    val uiState: StateFlow<StatsUiState> =
        combine(
            questRepository.getQuests(),
            questMetricsProvider.questMetrics
        ) { quests, metrics ->
            StatsUiState(
                completedSubquestsCount = metrics.completedSubquestsCount,
                completedPlannedTimeMinutes = metrics.completedPlannedTimeMinutes,
                totalPlannedTimeMinutes = metrics.totalPlannedTimeMinutes,
                totalQuestsCount = metrics.totalQuestsCount,
                totalSubQuestsCount = metrics.totalSubQuestsCount,
                remainingQuestsCount = metrics.remainingQuestsCount,
                estimationAccuracyPercentage = metrics.estimationAccuracyPercentage,
                quests = quests,
                isLoading = false
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = StatsUiState()
        )
}
