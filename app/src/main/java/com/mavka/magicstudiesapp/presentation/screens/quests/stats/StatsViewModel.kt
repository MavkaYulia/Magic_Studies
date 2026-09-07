package com.mavka.magicstudiesapp.presentation.screens.quests.stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mavka.magicstudiesapp.domain.models.PathModel
import com.mavka.magicstudiesapp.domain.provider.QuestOverviewProvider
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
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
    private val questOverviewProvider: QuestOverviewProvider,
) : ViewModel() {

    val uiState: StateFlow<StatsUiState> =
        questOverviewProvider.overview.map { snapshot ->
            val metrics = snapshot.metrics
            StatsUiState(
                completedSubquestsCount = metrics.completedSubquestsCount,
                completedPlannedTimeMinutes = metrics.completedPlannedTimeMinutes,
                totalPlannedTimeMinutes = metrics.totalPlannedTimeMinutes,
                totalQuestsCount = metrics.totalQuestsCount,
                totalSubQuestsCount = metrics.totalSubQuestsCount,
                remainingQuestsCount = metrics.remainingQuestsCount,
                estimationAccuracyPercentage = metrics.estimationAccuracyPercentage,
                quests = snapshot.quests,
                isLoading = false
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = StatsUiState()
        )
}
