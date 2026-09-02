package com.mavka.magicstudiesapp.presentation.screens.quests.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.mavka.magicstudiesapp.domain.models.Priority
import com.mavka.magicstudiesapp.domain.models.QuestModel
import com.mavka.magicstudiesapp.domain.models.SubQuest
import com.mavka.magicstudiesapp.domain.repository.QuestRepository
import com.mavka.magicstudiesapp.presentation.navigation.quests.DetailsRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val questRepository: QuestRepository
) : ViewModel() {

    private val route = savedStateHandle.toRoute<DetailsRoute>()
    val questId: Int = route.questId

    private val _filter = MutableStateFlow<QuestFilter>(QuestFilter.All)
    val filter: StateFlow<QuestFilter> = _filter

    private val _hideDone = MutableStateFlow(false)
    val hideDone: StateFlow<Boolean> = _hideDone

    val uiState: StateFlow<QuestModel?> = combine(
        questRepository.getQuest(questId),
        _filter,
        _hideDone
    ) { quest, filter, hideDone ->
        val filteredSubQuests = quest.subQuests.filter { sub ->
            val matchesFilter = when (filter) {
                QuestFilter.All -> true
                QuestFilter.Urgent -> sub.priority == Priority.URGENT
                QuestFilter.Normal -> sub.priority == Priority.NORMAL
                QuestFilter.Low -> sub.priority == Priority.LOW
            }
            val matchesDone = if (hideDone) !sub.isDone else true
            matchesFilter && matchesDone
        }
        quest.copy(subQuests = filteredSubQuests)

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    fun setFilter(filter: QuestFilter) {
        _filter.value = filter
    }

    fun toggleHideDone() {
        _hideDone.value = !_hideDone.value
    }

    fun addSubQuest(name: String, plannedTime: Float, priority: Priority) {
        val normalizedName = name.trim()
        if (normalizedName.isBlank() || plannedTime < 0f) return

        val newSubQuest = SubQuest(
            name = normalizedName,
            isDone = false,
            plannedTime = plannedTime,
            priority = priority
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

    fun toggleSubQuestDone(subQuest: SubQuest) {
        viewModelScope.launch {
            questRepository.updateQuest(questId, subQuest.copy(isDone = !subQuest.isDone))
        }
    }
}

sealed class QuestFilter {
    object All : QuestFilter()
    object Urgent : QuestFilter()
    object Normal : QuestFilter()
    object Low : QuestFilter()
}
