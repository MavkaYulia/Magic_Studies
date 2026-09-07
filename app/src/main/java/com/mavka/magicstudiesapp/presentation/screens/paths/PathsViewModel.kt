package com.mavka.magicstudiesapp.presentation.screens.paths

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mavka.magicstudiesapp.domain.models.PathModel
import com.mavka.magicstudiesapp.domain.models.QuestModel
import com.mavka.magicstudiesapp.domain.provider.PathIconProvider
import com.mavka.magicstudiesapp.domain.provider.PathOverviewProvider
import com.mavka.magicstudiesapp.domain.repository.PathRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.stateIn

class PathsViewModel(
    private val pathRepository: PathRepository,
    pathOverviewProvider: PathOverviewProvider,
    iconProvider: PathIconProvider
) : ViewModel() {

    private val availableIcons = iconProvider.getAvailableIcons()

    val uiState: StateFlow<PathsUiState> =
        pathOverviewProvider.overview.map { snapshot ->
            PathsUiState(
                paths = snapshot.paths,
                totalPathsCount = snapshot.metrics.totalPathsCount,
                remainingQuestsCount = snapshot.metrics.remainingQuestsCount,
                availableIcons = availableIcons,
                isLoading = false
            )
        }.stateInViewModel(viewModelScope, availableIcons)

    fun addPath(
        title: String,
        icon: Int,
        color: Int,
        quests: List<QuestModel>
    ) {
        val newPath = PathModel(
            title = title,
            icon = icon,
            color = color,
            quests = quests
        )

        viewModelScope.launch {
            pathRepository.addPath(newPath)
        }
    }
}

private fun Flow<PathsUiState>.stateInViewModel(
    scope: CoroutineScope,
    availableIcons: List<Int>
): StateFlow<PathsUiState> =
    stateIn(
        scope = scope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = PathsUiState(
            paths = emptyList(),
            totalPathsCount = 0,
            remainingQuestsCount = 0,
            availableIcons = availableIcons,
            isLoading = true
        )
    )

data class PathsUiState(
    val paths: List<PathModel> = emptyList(),
    val totalPathsCount: Int,
    val remainingQuestsCount: Int,
    val availableIcons: List<Int> = emptyList(),
    val isLoading: Boolean = true
)
