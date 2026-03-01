package com.mavka.magicstudiesapp.presentation.navigation

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mavka.magicstudiesapp.R
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicMaterialColor

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


    val scale by animateFloatAsState(
        targetValue = if (isSelected) 1.25f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "icon_scale"
    )

    val glowAlpha by animateFloatAsState(
        targetValue = if (isSelected) 0.25f else 0f,
        animationSpec = tween(durationMillis = 400),
        label = "icon_glow"
    )

    val interactionSource = remember { MutableInteractionSource() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                handleNavigation(navController, item.route, currentRoute)
            }
            .padding(vertical = dimensionResource(R.dimen.padding_small), horizontal = dimensionResource(R.dimen.padding_small))
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .drawBehind {
                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                MagicMaterialColor.primary.copy(alpha = glowAlpha),
                                Color.Transparent
                            ),
                            center = Offset(size.width / 2, size.height / 2),
                            radius = size.width * 1.2f
                        )
                    )
                }
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.title,
                tint = if (isSelected) MagicMaterialColor.primary else MagicMaterialColor.secondary,
                modifier = Modifier
                    .size(dimensionResource(R.dimen.icon_size_medium))
                    .scale(scale)
            )
        }

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_tiny)))

        Text(
            text = item.title,
            style = MaterialTheme.typography.labelSmall,
            color = if (isSelected) MagicMaterialColor.primary else MagicMaterialColor.secondary,
            modifier = Modifier.alpha(if (isSelected) 1f else 0.7f)
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