package com.mavka.magicstudiesapp.presentation.screens.quests

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mavka.magicstudiesapp.R
import com.mavka.magicstudiesapp.presentation.screens.quests.stats.StatsScreen
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicQuestTabSwitch
import com.mavka.magicstudiesapp.presentation.theme.designsystem.QuestTab
import kotlinx.coroutines.launch

@Composable
fun QuestContainer(
    onQuestClick: (Int) -> Unit
) {
    val tabs = listOf(
        QuestTab(stringResource(id = R.string.tab_quests), Icons.Default.Book),
        QuestTab(stringResource(id = R.string.tab_stats), Icons.Default.QueryStats)
    )
    val pagerState = rememberPagerState { tabs.size }
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        MagicQuestTabSwitch(
            modifier = Modifier.fillMaxWidth(),
            selectedIndex = pagerState.currentPage,
            items = tabs,
            onSelectionChange = { index ->
                coroutineScope.launch {
                    pagerState.animateScrollToPage(index)
                }
            }
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            when (page) {
                0 -> QuestsScreen(onQuestClick)
                1 -> StatsScreen()
            }
        }
    }
}
