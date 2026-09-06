package com.mavka.magicstudiesapp.domain.models

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

