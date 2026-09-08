package com.mavka.magicstudiesapp.domain.metrics

data class PathsMetrics(
    val completedQuestsCount: Int,
    val completedPlannedTimeMinutes: Int,
    val totalPlannedTimeMinutes: Int,
    val totalPathsCount: Int,
    val totalQuestsCount: Int,
    val remainingQuestsCount: Int,
    val estimationAccuracyPercentage: Double
)