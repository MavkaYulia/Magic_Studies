package com.mavka.magicstudiesapp.domain.models

data class PathModel(
    val id: Int = 0,
    val title: String,
    val icon: Int,
    val color: Int,
    val subQuests: List<SubQuest>
) {
    val completedSubQuestsCount: Int get() = subQuests.count { it.isDone }
    val totalSubQuestsCount: Int get() = subQuests.size
    val completedPlannedTimeMinutes: Int
        get() = subQuests.filter { it.isDone }.sumOf { it.plannedTime }

    val totalPlannedTimeMinutes: Int
        get() = subQuests.sumOf { it.plannedTime }
    val progress: Float get() = if (subQuests.isEmpty()) 0f else completedSubQuestsCount.toFloat() / totalSubQuestsCount
    val remaining = totalSubQuestsCount - completedSubQuestsCount

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
