package com.mavka.magicstudiesapp.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {
    @Serializable
    data object CentralHall : Screen

    @Serializable
    data object Atlas : Screen

    @Serializable
    data object Quests : Screen

    @Serializable
    data object Music : Screen

    @Serializable
    data object Timer : Screen
}