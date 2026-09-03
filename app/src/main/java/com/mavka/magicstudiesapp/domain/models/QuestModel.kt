package com.mavka.magicstudiesapp.domain.models

data class QuestModel(
    val id: Int = 0,
    val title: String,
    val icon: Int,
    val subQuests: List<SubQuest>
) {
    val completedSubQuestsCount: Int get() = subQuests.count { it.isDone }
    val totalSubQuestsCount: Int get() = subQuests.size
    val totalSpentTime: Float
        get() = subQuests.filter { it.isDone }.sumOf { it.plannedTime.toDouble() }.toFloat()
    val progress: Float get() = if (subQuests.isEmpty()) 0f else completedSubQuestsCount.toFloat() / totalSubQuestsCount

    val priority: Priority
        get() = when {
            subQuests.any { it.priority == Priority.URGENT } -> Priority.URGENT
            subQuests.any { it.priority == Priority.NORMAL } -> Priority.NORMAL
            else -> Priority.LOW
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
