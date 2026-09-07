package com.mavka.magicstudiesapp.presentation.screens.paths

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
import com.mavka.magicstudiesapp.presentation.screens.paths.stats.StatsScreen
import com.mavka.magicstudiesapp.presentation.theme.designsystem.MagicPathTabSwitch
import com.mavka.magicstudiesapp.presentation.theme.designsystem.PathTab
import kotlinx.coroutines.launch

@Composable
fun PathContainer(
    onPathClick: (Int) -> Unit
) {
    val tabs = listOf(
        PathTab(stringResource(id = R.string.tab_paths), Icons.Default.Book),
        PathTab(stringResource(id = R.string.tab_stats), Icons.Default.QueryStats)
    )
    val pagerState = rememberPagerState { tabs.size }
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        MagicPathTabSwitch(
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
                0 -> PathsScreen(onPathClick)
                1 -> StatsScreen()
            }
        }
    }
}
