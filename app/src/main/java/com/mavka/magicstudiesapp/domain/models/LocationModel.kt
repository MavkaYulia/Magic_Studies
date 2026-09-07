package com.mavka.magicstudiesapp.domain.models

data class LocationModel(
    val id: Int = 0,
    val name: String,
    val type: LocationType,
    val latitude: Double,
    val longitude: Double,
    val description: String = "",
    val isFavorite: Boolean = false,
)

enum class LocationType {
    LIBRARY,
    TAVERN,
    GARDEN,
    TOWER,
    BOOKSHOP,
    OTHER
}
