package com.mavka.magicstudiesapp.domain.models

import androidx.compose.ui.graphics.vector.ImageVector

data class QuestModel(
    val id: Int = 0,
    val title: String,
    val icon: ImageVector,
    val subQuests: List<SubQuest>
)

data class SubQuest(
    val id: Int = 0,
    val name: String,
    val isDone: Boolean,
    val plannedTime: Int
)
