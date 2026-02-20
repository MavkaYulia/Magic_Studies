package com.mavka.magicstudiesapp.presentation.navigation

sealed class Screen(val route: String) {
    object CentralHall: Screen("central_hall_screen")
    object Atlas: Screen("atlas_screen")
    object Quests: Screen("quests_screen")
    object Music: Screen("music_screen")
    object Timer: Screen("timer_screen")
}