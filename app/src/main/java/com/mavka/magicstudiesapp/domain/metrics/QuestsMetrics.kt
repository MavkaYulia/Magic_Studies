package com.mavka.magicstudiesapp.domain.metrics

data class QuestsMetrics(
    val completedSubquestsCount: Int,         // Загальна кількість зроблених підквестів
    val completedPlannedTimeMinutes: Int,     // Запланований час для виконаних підквестів
    val totalPlannedTimeMinutes: Int,         // Запланований час для всіх підквестів
    val totalQuestsCount: Int,                // Загальна кількість квестів
    val totalSubQuestsCount: Int,             // Загальна кількість підквестів
    val remainingQuestsCount: Int,            // Кількість квестів, що залишилися
    val estimationAccuracyPercentage: Double  // Точність естімації (фактичний vs запланований час)
)