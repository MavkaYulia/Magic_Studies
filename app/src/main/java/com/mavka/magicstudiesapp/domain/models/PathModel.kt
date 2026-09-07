package com.mavka.magicstudiesapp.domain.models

data class PathModel(
    val id: Int = 0,
    val title: String,
    val icon: Int,
    val color: Int,
    val quests: List<QuestModel>
) {
    val completedQuestsCount: Int get() = quests.count { it.isDone }
    val totalQuestsCount: Int get() = quests.size
    val completedPlannedTimeMinutes: Int
        get() = quests.filter { it.isDone }.sumOf { it.plannedTime }

    val totalPlannedTimeMinutes: Int
        get() = quests.sumOf { it.plannedTime }
    val progress: Float get() = if (quests.isEmpty()) 0f else completedQuestsCount.toFloat() / totalQuestsCount
    val remaining = totalQuestsCount - completedQuestsCount

    val getPriority: Priority
        get() {
            var priority = Priority.LOW

            for (quest in quests) {
                if (!quest.isDone && quest.priority.ordinal < priority.ordinal) {
                    priority = quest.priority
                }
            }

            return priority
        }
}
