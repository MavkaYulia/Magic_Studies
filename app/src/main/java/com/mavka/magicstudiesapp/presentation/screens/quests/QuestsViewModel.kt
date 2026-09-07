package com.mavka.magicstudiesapp.presentation.screens.quests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mavka.magicstudiesapp.domain.models.PathModel
import com.mavka.magicstudiesapp.domain.models.SubQuest
import com.mavka.magicstudiesapp.domain.provider.QuestIconProvider
import com.mavka.magicstudiesapp.domain.provider.QuestOverviewProvider
import com.mavka.magicstudiesapp.domain.repository.QuestRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.stateIn

class QuestsViewModel(
    private val questRepository: QuestRepository,
    questOverviewProvider: QuestOverviewProvider,
    iconProvider: QuestIconProvider
) : ViewModel() {

    private val availableIcons = iconProvider.getAvailableIcons()

    val uiState: StateFlow<QuestsUiState> =
        questOverviewProvider.overview.map { snapshot ->
            QuestsUiState(
                quests = snapshot.quests,
                totalQuestsCount = snapshot.metrics.totalQuestsCount,
                remainingQuestsCount = snapshot.metrics.remainingQuestsCount,
                availableIcons = availableIcons,
                isLoading = false
            )
        }.stateInViewModel(viewModelScope, availableIcons)

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

private fun Flow<QuestsUiState>.stateInViewModel(
    scope: CoroutineScope,
    availableIcons: List<Int>
): StateFlow<QuestsUiState> =
    stateIn(
        scope = scope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = QuestsUiState(
            quests = emptyList(),
            totalQuestsCount = 0,
            remainingQuestsCount = 0,
            availableIcons = availableIcons,
            isLoading = true
        )
    )

data class QuestsUiState(
    val quests: List<PathModel> = emptyList(),
    val totalQuestsCount: Int,
    val remainingQuestsCount: Int,
    val availableIcons: List<Int> = emptyList(),
    val isLoading: Boolean = true
)
