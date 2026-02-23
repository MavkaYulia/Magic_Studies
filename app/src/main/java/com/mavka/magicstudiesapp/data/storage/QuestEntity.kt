package com.mavka.magicstudiesapp.data.storage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "quests")
data class QuestEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val icon: Int,
    val orderNumber: Int
)

@Entity(
    tableName = "sub_quests",
    foreignKeys = [
        ForeignKey(
            entity = QuestEntity::class,
            parentColumns = ["id"],
            childColumns = ["quest_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["quest_id"])]
)
data class SubQuestEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "quest_id")
    val questId: Int,
    val name: String,
    @ColumnInfo(name = "is_done")
    val isDone: Boolean,
    val plannedTime: Int
)

