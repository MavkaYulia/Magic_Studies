package com.mavka.magicstudiesapp.domain.models

import androidx.compose.ui.graphics.vector.ImageVector

data class QuestModel(
    val id: Int = 0,
    val title: String,
    val icon: ImageVector,
    val subQuests: List<SubQuest>
) {
    val completedSubQuestsCount: Int get() = subQuests.count { it.isDone }
    val totalSubQuestsCount: Int get() = subQuests.size
    val totalSpentTime: Int get() = subQuests.filter { it.isDone }.sumOf { it.plannedTime }
    val progress: Float get() = if (subQuests.isEmpty()) 0f else completedSubQuestsCount.toFloat() / totalSubQuestsCount
}

data class SubQuest(
    val id: Int = 0,
    val name: String,
    val isDone: Boolean,
    val plannedTime: Int,
    val priority: Priority = Priority.NORMAL
)

enum class Priority{
    URGENT,
    NORMAL,
    LOW
}
