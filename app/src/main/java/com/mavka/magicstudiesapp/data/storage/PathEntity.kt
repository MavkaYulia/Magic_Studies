package com.mavka.magicstudiesapp.data.storage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

import com.mavka.magicstudiesapp.domain.models.Priority

@Entity(tableName = "paths")
data class PathEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val icon: Int,
    val color: Int = 0
)

@Entity(
    tableName = "quests",
    foreignKeys = [
        ForeignKey(
            entity = PathEntity::class,
            parentColumns = ["id"],
            childColumns = ["path_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["path_id"])]
)
data class QuestEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "path_id")
    val pathId: Int,
    val name: String,
    @ColumnInfo(name = "is_done")
    val isDone: Boolean,
    val plannedTime: Int,
    val priority: Priority = Priority.NORMAL
)

