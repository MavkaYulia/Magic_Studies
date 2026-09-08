package com.mavka.magicstudiesapp.presentation.navigation.paths

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mavka.magicstudiesapp.presentation.navigation.Screen
import com.mavka.magicstudiesapp.presentation.screens.paths.details.DetailsScreen
import com.mavka.magicstudiesapp.presentation.screens.paths.PathContainer
import kotlinx.serialization.Serializable

@Serializable
internal object PathContainerRoute

@Serializable
internal data class DetailsRoute(val pathId: Int)

fun NavGraphBuilder.pathsNavGraph(navController: NavHostController) {
    navigation<Screen.Paths>(startDestination = PathContainerRoute) {

        composable<PathContainerRoute> {
            PathContainer(
                onPathClick = { pathId ->
                    navController.navigate(DetailsRoute(pathId = pathId))
                }
            )
        }

        composable<DetailsRoute> {
            DetailsScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
