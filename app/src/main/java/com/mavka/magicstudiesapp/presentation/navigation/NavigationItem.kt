package com.mavka.magicstudiesapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: Screen,
)

val navigationItems = listOf(

    NavigationItem(
        title = "Atlas",
        icon = Icons.Default.LocationOn,
        route = Screen.Atlas
    ),
    NavigationItem(
        title = "Quests",
        icon = Icons.Default.MailOutline,
        route = Screen.Quests
    ),
    NavigationItem(
        title = "CentralHall",
        icon = Icons.Default.Home,
        route = Screen.CentralHall
    ),
    NavigationItem(
        title = "Timer",
        icon = Icons.Default.Notifications,
        route = Screen.Timer
    ),
    NavigationItem(
        title = "Music",
        icon = Icons.Default.Star,
        route = Screen.Music
    ),
)