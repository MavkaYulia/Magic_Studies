package com.mavka.magicstudiesapp.presentation.screens.quests

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import com.mavka.magicstudiesapp.domain.models.Difficulty
import com.mavka.magicstudiesapp.domain.models.QuestModel
import com.mavka.magicstudiesapp.domain.models.SubQuest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class QuestsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(QuestUiState())
    val uiState: StateFlow<QuestUiState> = _uiState

    private var currentQuests = listOf(
        QuestModel(
            id = 5551,
            title = "quest1",
            icon = Icons.Default.DateRange,
            subQuests = listOf(
                SubQuest("підтаска1", true, Difficulty.LOW),
                SubQuest("підтаска2", true, Difficulty.MEDIUM),
                SubQuest("підтаска3", true, Difficulty.HARD)
            )
        ),
        QuestModel(
            id = 555,
            title = "quest2",
            icon = Icons.Default.Build,
            subQuests = listOf(
                SubQuest("підтаска1", true, Difficulty.LOW),
                SubQuest("підтаска2", true, Difficulty.MEDIUM),
                SubQuest("підтаска3", true, Difficulty.HARD)
            )
        )
    )

    init {
        loadQuests()
    }

    fun loadQuests() {
        _uiState.update { it.copy(quests = currentQuests, isLoading = false) }
    }

    fun updateQuest(id: Int, newTitle: String) {
        _uiState.update { currentState ->
            val updatedList = currentState.quests.map { quest ->
                if (quest.id == id) {
                    quest.copy(title = newTitle)
                } else {
                    quest
                }
            }
            currentState.copy(quests = updatedList)
        }
    }


    fun addQuest(title: String, icon: ImageVector, subQuests: List<SubQuest>) {
        val newQuest = QuestModel(title.hashCode(), title, icon, subQuests)
        _uiState.update { it.copy(quests = it.quests + newQuest) }
    }


    fun deleteQuest(id: Int) {
        _uiState.update { currentState ->
            val filteredList = currentState.quests.filter { it.id != id }
            currentState.copy(quests = filteredList)
        }
    }
}

data class QuestUiState(
    val quests: List<QuestModel> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)