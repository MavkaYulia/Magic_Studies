package com.mavka.magicstudiesapp.domain.models

import androidx.annotation.DrawableRes

data class LocationModel(
    val id: Int = 0,
    val name: String,
    val type: HavenType = HavenType.OTHER,
    val description: String = "",
    @param:DrawableRes val imageRes: Int,
    val isFavorite: Boolean = false,
)

enum class HavenType(val title: String, val emoji: String) {
    LIBRARY("Library", "📚"),
    TAVERN("Tavern", "🍺"),
    GARDEN("Garden", "🌿"),
    TOWER("Wizard Tower", "🏰"),
    BOOKSHOP("Bookshop", "📜"),
    OTHER("Haven", "📍")
}