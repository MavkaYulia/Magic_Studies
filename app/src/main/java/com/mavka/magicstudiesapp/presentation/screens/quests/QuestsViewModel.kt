package com.mavka.magicstudiesapp.presentation.screens.quests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mavka.magicstudiesapp.domain.models.PathModel
import com.mavka.magicstudiesapp.domain.models.SubQuest
import com.mavka.magicstudiesapp.domain.provider.QuestIconProvider
import com.mavka.magicstudiesapp.domain.provider.QuestMetricsProvider
import com.mavka.magicstudiesapp.domain.repository.QuestRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class QuestsViewModel(
    private val questRepository: QuestRepository,
    questMetricsProvider: QuestMetricsProvider,
    iconProvider: QuestIconProvider
) : ViewModel() {

    private val availableIcons = iconProvider.getAvailableIcons()

    val uiState: StateFlow<QuestsUiState> =
        combine(
            questRepository.getQuests(),
            questMetricsProvider.questMetrics
        ) { quests, metrics ->
            QuestsUiState(
                quests = quests,
                totalQuestsCount = metrics.totalQuestsCount,
                remainingQuestsCount = metrics.remainingQuestsCount,
                availableIcons = availableIcons,
                isLoading = false
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = QuestsUiState(
                quests = emptyList(),
                totalQuestsCount = 0,
                remainingQuestsCount = 0,
                availableIcons = availableIcons,
                isLoading = true
            )
        )

    fun addQuest(
        title: String,
        icon: Int,
        color: Int,
        subQuests: List<SubQuest>
    ) {
        val newQuest = PathModel(
            title = title,
            icon = icon,
            color = color,
            subQuests = subQuests
        )

        viewModelScope.launch {
            questRepository.addQuest(newQuest)
        }
    }
}

data class QuestsUiState(
    val quests: List<PathModel> = emptyList(),
    val totalQuestsCount: Int,
    val remainingQuestsCount: Int,
    val availableIcons: List<Int> = emptyList(),
    val isLoading: Boolean = true
)
