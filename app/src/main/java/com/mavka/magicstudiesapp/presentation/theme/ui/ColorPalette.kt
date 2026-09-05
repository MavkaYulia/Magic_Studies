package com.mavka.magicstudiesapp.presentation.theme.ui

import androidx.compose.ui.graphics.Color

object ColorPalette {
    val Gold = Color(0xFFC29F42)
    val Green = Color(0xFF5E8047)
    val Blue = Color(0xFF416B9E)
    val Brown = Color(0xFF806B4E)
    val Crimson = Color(0xFF862B2C)
    val Purple = Color(0xFF6B428A)
    val Teal = Color(0xFF387A75)
    val Orange = Color(0xFFB86629)
    val Olive = Color(0xFF827D3B)
    val Silver = Color(0xFF788596)
    val Plum = Color(0xFF5E354A)
    val Terracotta = Color(0xFF9E523A)

    val all = listOf(
        Gold,
        Green,
        Blue,
        Brown,
        Crimson,
        Purple,
        Teal,
        Orange,
        Olive,
        Silver,
        Plum,
        Terracotta
    )

    fun getRandom(): Color {
        return all.random()
    }

    fun getAt(index: Int): Color {
        return all[index % all.size]
    }
}