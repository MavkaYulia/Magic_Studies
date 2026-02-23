package com.mavka.magicstudiesapp.data.mapper

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

class IconMapper {


    private val iconMap = mapOf(
        1 to Icons.Default.Home,
        2 to Icons.Default.Star,
        3 to Icons.Default.CheckCircle,
        4 to Icons.Default.Settings,
        5 to Icons.Default.LocationOn
    )

    private val idMap = iconMap.entries.associate { it.value to it.key }

    fun getVectorById(id: Int): ImageVector {
        return iconMap[id] ?: Icons.Default.QuestionMark
    }

    fun getIdByVector(vector: ImageVector): Int {
        return idMap[vector] ?: 0
    }
}