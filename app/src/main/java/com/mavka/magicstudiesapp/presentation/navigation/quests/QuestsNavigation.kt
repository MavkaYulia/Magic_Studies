package com.mavka.magicstudiesapp.presentation.navigation.quests

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mavka.magicstudiesapp.presentation.navigation.Screen
import com.mavka.magicstudiesapp.presentation.screens.quests.details.DetailsScreen
import com.mavka.magicstudiesapp.presentation.screens.quests.QuestContainer
import kotlinx.serialization.Serializable

@Serializable
internal object QuestContainerRoute

@Serializable
internal data class DetailsRoute(val questId: Int)

fun NavGraphBuilder.questsNavGraph(navController: NavHostController) {
    navigation<Screen.Quests>(startDestination = QuestContainerRoute) {

        composable<QuestContainerRoute> {
            QuestContainer(
                onQuestClick = { questId ->
                    navController.navigate(DetailsRoute(questId = questId))
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
