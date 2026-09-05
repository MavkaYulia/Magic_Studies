package com.mavka.magicstudiesapp.presentation.screens.quests.stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mavka.magicstudiesapp.domain.models.QuestModel
import com.mavka.magicstudiesapp.domain.repository.QuestRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

data class StatsState(
    val totalQuests: Int = 0,
    val totalHours: Float = 0f,
    val totalSessions: Int = 0,
    val completionRate: Float = 0f,
    val quests: List<QuestModel> = emptyList(),
    val isLoading: Boolean = true
)

class StatsViewModel(
    private val questRepository: QuestRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(StatsState())
    val uiState: StateFlow<StatsState> = _state.asStateFlow()

    init {
        loadStats()
    }

    private fun loadStats() {
        questRepository.getQuests()
            .onEach { quests ->
                val totalQuests = quests.size
                val totalHours = quests.sumOf { it.totalSpentTime.toDouble() }.toFloat()
                val totalSubQuests = quests.sumOf { it.totalSubQuestsCount }
                val completedSubQuests = quests.sumOf { it.completedSubQuestsCount }
                val completionRate = if (totalSubQuests > 0) {
                    completedSubQuests.toFloat() / totalSubQuests
                } else 0f

                _state.update {
                    it.copy(
                        totalQuests = totalQuests,
                        totalHours = totalHours,
                        totalSessions = totalSubQuests,
                        completionRate = completionRate,
                        quests = quests,
                        isLoading = false
                    )
                }
            }
            .launchIn(viewModelScope)
    }
}
