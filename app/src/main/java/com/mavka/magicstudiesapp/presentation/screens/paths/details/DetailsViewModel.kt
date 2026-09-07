package com.mavka.magicstudiesapp.presentation.screens.paths.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.mavka.magicstudiesapp.domain.models.PathModel
import com.mavka.magicstudiesapp.domain.models.Priority
import com.mavka.magicstudiesapp.domain.models.Quest
import com.mavka.magicstudiesapp.domain.repository.PathRepository
import com.mavka.magicstudiesapp.presentation.navigation.paths.DetailsRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val pathRepository: PathRepository
) : ViewModel() {

    private val route = savedStateHandle.toRoute<DetailsRoute>()
    val pathId: Int = route.pathId

    private val _filter = MutableStateFlow<QuestFilter>(QuestFilter.All)
    val filter: StateFlow<QuestFilter> = _filter

    private val _hideDone = MutableStateFlow(false)
    val hideDone: StateFlow<Boolean> = _hideDone

    val uiState: StateFlow<PathModel?> =
        combine(
            pathRepository.getPath(pathId),
            _filter,
            _hideDone
        ) { path, filter, hideDone ->
            path?.filterQuests(filter, hideDone)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    fun setFilter(filter: QuestFilter) {
        _filter.value = filter
    }

    fun toggleHideDone() {
        _hideDone.update { !it }
    }

    fun addQuest(
        name: String,
        plannedTime: Int,
        priority: Priority
    ) {
        val normalizedName = name.trim()
        if (normalizedName.isBlank() || plannedTime < 0) return

        viewModelScope.launch {
            pathRepository.addQuest(
                pathId,
                Quest(
                    name = normalizedName,
                    isDone = false,
                    plannedTime = plannedTime,
                    priority = priority
                )
            )
        }
    }

    fun deleteQuest(questId: Int) {
        viewModelScope.launch {
            pathRepository.deleteQuest(questId)
        }
    }

    fun deletePath() {
        viewModelScope.launch {
            pathRepository.deletePath(pathId)
        }
    }

    fun toggleQuestDone(quest: Quest) {
        viewModelScope.launch {
            pathRepository.updateQuest(
                pathId,
                quest.copy(isDone = !quest.isDone)
            )
        }
    }

}

fun PathModel.filterQuests(
    filter: QuestFilter,
    hideDone: Boolean
): PathModel =
    copy(
        quests = quests.filter { q ->
            val matchesFilter = when (filter) {
                QuestFilter.All -> true
                QuestFilter.Urgent -> q.priority == Priority.URGENT
                QuestFilter.Normal -> q.priority == Priority.NORMAL
                QuestFilter.Low -> q.priority == Priority.LOW
            }

            val matchesDone = !hideDone || !q.isDone

            matchesFilter && matchesDone
        }
    )

sealed class QuestFilter {
    object All : QuestFilter()
    object Urgent : QuestFilter()
    object Normal : QuestFilter()
    object Low : QuestFilter()
}
