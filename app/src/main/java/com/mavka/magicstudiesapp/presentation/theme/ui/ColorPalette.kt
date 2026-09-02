package com.mavka.magicstudiesapp.presentation.theme.ui

import androidx.compose.ui.graphics.Color

object ColorPalette {
    val Gold = Color(0xFFC29F42)
    val Green = Color(0xFF5E8047)
    val Blue = Color(0xFF416B9E)
    val Brown = Color(0xFF806B4E)
    val Crimson = Color(0xFF862B2C)

    val all = listOf(
        Gold,
        Green,
        Blue,
        Brown,
        Crimson
    )

    fun getRandom(): Color {
        return all.random()
    }

    fun getAt(index: Int): Color {
        return all[index % all.size]
    }
}