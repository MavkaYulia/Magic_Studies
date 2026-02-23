package com.mavka.magicstudiesapp.presentation.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.mavka.magicstudiesapp.data.mapper.IconMapper
import com.mavka.magicstudiesapp.data.repository.QuestRepositoryImpl
import com.mavka.magicstudiesapp.data.storage.AppDatabase
import com.mavka.magicstudiesapp.presentation.navigation.AppNavigation
import com.mavka.magicstudiesapp.presentation.navigation.BottomNavigationBar
import com.mavka.magicstudiesapp.presentation.screens.quests.QuestsViewModel

class MainActivity : ComponentActivity() {

    private val dp by lazy { //todo temp
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "magic_studies_database"
        ).allowMainThreadQueries().build()
    }
     val activityViewModel by viewModels<QuestsViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    var repository =
                        QuestRepositoryImpl(questDao = dp.questDao(), mapper = IconMapper())
                    return QuestsViewModel(repository) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen(activityViewModel)
        }
    }
}

@Composable
fun MainScreen(questsViewModel: QuestsViewModel) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        AppNavigation(
            questsViewModel = questsViewModel,
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}