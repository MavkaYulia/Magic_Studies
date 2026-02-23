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

    fun getSubQuestsSize() = uiState.value.quests.sumOf { quest ->
        quest.subQuests.count { !it.isDone }
    }

    fun getQuestsSize() = uiState.value.quests.size

    fun addQuest(title: String, icon: ImageVector, order: Int, subQuests: List<SubQuest>) {
        val newQuest = QuestModel(title = title, icon = icon, order = order, subQuests = subQuests)
        viewModelScope.launch {
            questRepository.addQuest(newQuest)
        }
    }

}

data class QuestUiState(
    val quests: List<QuestModel>,
    val isLoading: Boolean,
    val errorMessage: String?
)
