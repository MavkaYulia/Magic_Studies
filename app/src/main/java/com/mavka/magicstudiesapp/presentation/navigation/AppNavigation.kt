package com.mavka.magicstudiesapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mavka.magicstudiesapp.presentation.navigation.quests.questsNavGraph
import com.mavka.magicstudiesapp.presentation.screens.atlas.AtlasScreen
import com.mavka.magicstudiesapp.presentation.screens.hall.CentralHallScreen
import com.mavka.magicstudiesapp.presentation.screens.music.MusicScreen
import com.mavka.magicstudiesapp.presentation.screens.timer.TimerScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.CentralHall,
        modifier = modifier
    ) {
        composable<Screen.CentralHall> {
            CentralHallScreen()
        }
        composable<Screen.Atlas> {
            AtlasScreen()
        }

        questsNavGraph(navController)

        composable<Screen.Music> {
            MusicScreen()
        }
        composable<Screen.Timer> {
            TimerScreen()
        }
    }
}
