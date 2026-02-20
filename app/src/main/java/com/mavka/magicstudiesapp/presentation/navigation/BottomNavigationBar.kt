package com.mavka.magicstudiesapp.presentation.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Surface(
        color = Color(0xFFEFE2B9),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp, top = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            navigationItems.forEach { item ->
                StandardItem(item, currentRoute, navController)
            }
        }
    }
}

@Composable
fun StandardItem(item: NavigationItem, currentRoute: String?, navController: NavController) {
    val isSelected = currentRoute == item.route

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { handleNavigation(navController, item.route, currentRoute) }
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = item.title,
            tint = if (isSelected) Color(0xFF2F2312) else Color(0xFF8B7A5E),
            modifier = Modifier.size(26.dp)
        )
        Text(
            text = item.title,
            style = MaterialTheme.typography.labelSmall,
            color = if (isSelected) Color(0xFF634D2E) else Color(0xFF8B7A5E)
        )
    }
}

private fun handleNavigation(navController: NavController, route: String, currentRoute: String?) {
    if (currentRoute != route) {
        navController.navigate(route) {
            popUpTo(navController.graph.startDestinationId) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}