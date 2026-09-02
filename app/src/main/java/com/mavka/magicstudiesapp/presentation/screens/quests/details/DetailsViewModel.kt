package com.mavka.magicstudiesapp.presentation.screens.quests.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.mavka.magicstudiesapp.domain.models.QuestModel
import com.mavka.magicstudiesapp.domain.models.SubQuest
import com.mavka.magicstudiesapp.domain.repository.QuestRepository
import com.mavka.magicstudiesapp.presentation.navigation.quests.DetailsRoute
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val questRepository: QuestRepository
) : ViewModel() {

    private val route = savedStateHandle.toRoute<DetailsRoute>()
    val questId: Int = route.questId

    val uiState: StateFlow<QuestModel?> = questRepository.getQuest(questId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    fun addSubQuest(questId: Int, subName: String, subPlannedTime: Int) {
        val normalizedName = subName.trim()
        if (normalizedName.isBlank() || subPlannedTime <= 0) return

        val newSubQuest = SubQuest(
            name = normalizedName,
            isDone = false,
            plannedTime = subPlannedTime
        )
        viewModelScope.launch {
            questRepository.addSubQuest(questId, newSubQuest)
        }
    }

    fun deleteSubQuest(subQuestId: Int) {

        viewModelScope.launch {
            questRepository.deleteSubQuest(subQuestId)
        }
    }

    fun updateSubQuest(questId: Int, subQuest: SubQuest) {

        viewModelScope.launch {
            questRepository.updateQuest(questId, subQuest)
        }
    }

}