package com.mavka.magicstudiesapp.domain.models

import androidx.compose.ui.graphics.Color

data class QuestModel(
    val id: Int = 0,
    val title: String,
    val icon: Int,
    val color: Color,
    val subQuests: List<SubQuest>
) {
    val completedSubQuestsCount: Int get() = subQuests.count { it.isDone }
    val totalSubQuestsCount: Int get() = subQuests.size
    val totalSpentTime: Float
        get() = subQuests.filter { it.isDone }.sumOf { it.plannedTime.toDouble() }.toFloat()
    val progress: Float get() = if (subQuests.isEmpty()) 0f else completedSubQuestsCount.toFloat() / totalSubQuestsCount

    val getPriority: Priority
        get() {
            var priority = Priority.LOW

            for (quest in subQuests) {
                if (!quest.isDone && quest.priority.ordinal < priority.ordinal) {
                    priority = quest.priority
                }
            }

            return priority
        }
}

data class SubQuest(
    val id: Int = 0,
    val name: String,
    val isDone: Boolean,
    val plannedTime: Float,
    val priority: Priority = Priority.NORMAL
)

enum class Priority {
    URGENT,
    NORMAL,
    LOW
}
