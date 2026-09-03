package com.mavka.magicstudiesapp.data.mapper

import com.mavka.magicstudiesapp.R
import com.mavka.magicstudiesapp.domain.provider.QuestIconProvider

class IconMapper : QuestIconProvider {

    private val iconMap = mapOf(
        1 to R.drawable.img_magic_1,
        2 to R.drawable.img_magic_2,
        3 to R.drawable.img_magic_3,
        4 to R.drawable.img_magic_4,
        5 to R.drawable.img_magic_5,
        6 to R.drawable.img_magic_6,
        7 to R.drawable.img_magic_7,
        8 to R.drawable.img_magic_8,
        9 to R.drawable.img_magic_9,
        10 to R.drawable.img_magic_10,
        11 to R.drawable.img_magic_11,
        12 to R.drawable.img_magic_12,
        13 to R.drawable.img_magic_13,
        14 to R.drawable.img_magic_14,
        15 to R.drawable.img_magic_15,
        16 to R.drawable.img_magic_16,
        17 to R.drawable.img_magic_17,
        18 to R.drawable.img_magic_18,
    )

    private val idMap = iconMap.entries.associate { it.value to it.key }

    override fun getAvailableIcons(): List<Int> = iconMap.values.toList()

    fun getIconById(id: Int): Int {
        return iconMap[id] ?: R.drawable.img_magic_9
    }

    fun getIdByIcon(icon: Int): Int {
        return idMap[icon] ?: 0
    }
}