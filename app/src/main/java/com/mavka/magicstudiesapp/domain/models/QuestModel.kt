package com.mavka.magicstudiesapp.domain.models

import androidx.compose.ui.graphics.vector.ImageVector

data class QuestModel(
    val id: Int,
    val title: String,
    val icon: ImageVector,
    val subQuests: List<SubQuest>
)

data class SubQuest(
    val name: String,
    val isDone: Boolean,
    val difficulty: Difficulty
)

enum class Difficulty {
    EASY, MEDIUM, HARD
}

val Difficulty.time: Int
    get() = when (this) {
        Difficulty.EASY -> 1
        Difficulty.MEDIUM -> 2
        Difficulty.HARD -> 4
    }