package com.mavka.magicstudiesapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Castle
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Style
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String,
)

val navigationItems = listOf(

    NavigationItem(
        title = "Atlas",
        icon = Icons.Default.Explore,
        route = Screen.Atlas.route
    ),
    NavigationItem(
        title = "Quests",
        icon = Icons.Default.Style,
        route = Screen.Quests.route
    ),
    NavigationItem(
        title = "CentralHall",
        icon = Icons.Default.Castle,
        route = Screen.CentralHall.route
    ),
    NavigationItem(
        title = "Timer",
        icon = Icons.Default.HourglassEmpty,
        route = Screen.Timer.route
    ),
    NavigationItem(
        title = "Music",
        icon = Icons.Default.LibraryMusic,
        route = Screen.Music.route
    ),
)