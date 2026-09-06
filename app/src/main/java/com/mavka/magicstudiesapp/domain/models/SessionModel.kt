package com.mavka.magicstudiesapp.domain.models

import java.time.LocalDate
import java.time.LocalDateTime

data class SessionQuestRecord(
    val questId: Int,
    val completedSubQuestIds: List<Int>
)
data class FocusSession(
    val id: Long = 0,
    val locationId: Int? = null,
    val durationMinutes: Int,
    val timestamp: LocalDateTime = LocalDateTime.now(), // TODO замінити на інт
    val streakDays: Int = 0,
    val lastActiveDate: LocalDate? = null,
    val questRecords: List<SessionQuestRecord> = emptyList()
) {

    val isMorningMagic: Boolean get() = timestamp.hour in 5..11
    val isNightSpell: Boolean get() = timestamp.hour in 21..23 || timestamp.hour in 0..4
    val isDeepWork: Boolean get() = durationMinutes >= 45
    val isQuickQuest: Boolean get() = durationMinutes <= 20
    val hasLocation: Boolean get() = locationId != null
}


