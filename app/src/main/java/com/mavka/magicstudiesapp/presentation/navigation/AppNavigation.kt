package com.mavka.magicstudiesapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mavka.magicstudiesapp.presentation.screens.atlas.AtlasScreen
import com.mavka.magicstudiesapp.presentation.screens.hall.CentralHallScreen
import com.mavka.magicstudiesapp.presentation.screens.music.MusicScreen
import com.mavka.magicstudiesapp.presentation.screens.quests.QuestsScreen
import com.mavka.magicstudiesapp.presentation.screens.timer.TimerScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.CentralHall.route,
        modifier = modifier
    ) {
        composable(Screen.CentralHall.route) {
            CentralHallScreen()
        }

        composable(Screen.Atlas.route) {
            AtlasScreen()
        }

        composable(Screen.Quests.route) {
            QuestsScreen()
        }

        composable(Screen.Timer.route) {
            TimerScreen()
        }

        composable(Screen.Music.route) {
            MusicScreen()
        }

    }
}