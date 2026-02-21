package com.mavka.magicstudiesapp.domain.models

import android.graphics.drawable.Icon
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
    LOW, MEDIUM, HARD
}