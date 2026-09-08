package com.mavka.magicstudiesapp.domain.models

import java.time.Instant

data class QuestModel(
    val id: Int = 0,
    val name: String,
    val isDone: Boolean,
    val plannedTime: Int,
    val createdAt: Instant? = null,
    val completedAt: Instant? = null,
    val priority: Priority = Priority.NORMAL
)

enum class Priority {
    URGENT,
    NORMAL,
    LOW
}


