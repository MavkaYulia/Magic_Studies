package com.mavka.magicstudiesapp.domain.models

import java.time.Duration
import java.time.Instant
import java.time.ZoneId

data class SessionModel(
    val id: Long = 0,
    val locationId: Long? = null,
    val type: SessionType,
    val startAt: Instant,
    val endAt: Instant? = null,
    val isCancelled: Boolean = false
) {
    private val localStartHour: Int
        get() = startAt.atZone(ZoneId.systemDefault()).hour

    val isMorningMagic: Boolean
        get() = localStartHour in 5..11

    val isNightSpell: Boolean
        get() = localStartHour in 21..23 || localStartHour in 0..4

    val hasLocation: Boolean
        get() = locationId != null

    val isActive: Boolean
        get() = endAt == null && !isCancelled

    val durationMinutes: Long?
        get() = endAt?.let { Duration.between(startAt, it).toMinutes() }
}

data class SessionType(
    val category: SessionCategory,
    val cycle: Int = 1
){
    val calculatedDurationMinutes: Int
        get() = when (category) {
            SessionCategory.CLASSIC_POMODORO -> 25 * cycle
            SessionCategory.QUICK_SPRINT -> 15 * cycle
            SessionCategory.DEEP_FOCUS -> 50 * cycle
            SessionCategory.MARATHON -> 90 * cycle
        }
}

enum class SessionCategory {
    CLASSIC_POMODORO,
    DEEP_FOCUS,
    QUICK_SPRINT,
    MARATHON
}
