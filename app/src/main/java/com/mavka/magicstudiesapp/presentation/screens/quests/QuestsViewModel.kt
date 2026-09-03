package com.mavka.magicstudiesapp.presentation.screens.quests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mavka.magicstudiesapp.domain.models.QuestModel
import com.mavka.magicstudiesapp.domain.models.SubQuest
import com.mavka.magicstudiesapp.domain.provider.QuestIconProvider
import com.mavka.magicstudiesapp.domain.repository.QuestRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuestsViewModel(
    private val questRepository: QuestRepository,
    iconProvider: QuestIconProvider
) : ViewModel() {
    private val _uiState =
        MutableStateFlow(
            QuestUiState(
                quests = listOf(),
                availableIcons = iconProvider.getAvailableIcons(),
                isLoading = true,
                errorMessage = null
            )
        )
    val uiState: StateFlow<QuestUiState> = _uiState

    init {
        viewModelScope.launch {
            questRepository.getQuests().collect { quests ->
                _uiState.update {
                    it.copy(
                        quests = quests,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun addQuest(title: String, icon: Int, subQuests: List<SubQuest>) {
        val newQuest = QuestModel(title = title, icon = icon, subQuests = subQuests)
        viewModelScope.launch {
            questRepository.addQuest(newQuest)
        }
    }


    fun deleteQuest(questId: Int) {
        viewModelScope.launch {
            questRepository.deleteQuest(questId)
        }
    }

    fun sumOfDoneSubQuest() = uiState.value.quests.sumOf { quest ->
        quest.subQuests.count { !it.isDone }
    }

}

data class QuestUiState(
    val quests: List<QuestModel>,
    val availableIcons: List<Int> = emptyList(),
    val isLoading: Boolean,
    val errorMessage: String?
)
