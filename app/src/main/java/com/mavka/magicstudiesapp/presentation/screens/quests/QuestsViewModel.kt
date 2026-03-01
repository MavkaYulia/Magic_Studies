package com.mavka.magicstudiesapp.presentation.screens.quests

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mavka.magicstudiesapp.domain.models.QuestModel
import com.mavka.magicstudiesapp.domain.models.SubQuest
import com.mavka.magicstudiesapp.domain.repository.QuestRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuestsViewModel(private val questRepository: QuestRepository) : ViewModel() {
    private val _uiState =
        MutableStateFlow(QuestUiState(quests = listOf(), isLoading = true, errorMessage = null))
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

    fun addQuest(title: String, icon: ImageVector, subQuests: List<SubQuest>) {
        val newQuest = QuestModel(title = title, icon = icon, subQuests = subQuests)
        viewModelScope.launch {
            questRepository.addQuest(newQuest)
        }
    }

    fun addSubQuest(questId: Int, subName: String, subPlannedTime: Int) {
        val newSubQuest = SubQuest(name = subName, isDone = false, plannedTime = subPlannedTime)
        viewModelScope.launch {
            questRepository.addSubQuest(questId, newSubQuest)
        }
    }

    fun deleteSubQuest(subQuestId: Int) {

        viewModelScope.launch {
            questRepository.deleteSubQuest(subQuestId)
        }
    }

    fun deleteQuest(questId: Int) {
        viewModelScope.launch {
            questRepository.deleteQuest(questId)
        }
    }

}

data class QuestUiState(
    val quests: List<QuestModel>,
    val isLoading: Boolean,
    val isSelectionMode: Boolean = false,
    val selectedIds: Set<Int> = emptySet(),
    val errorMessage: String?
)
