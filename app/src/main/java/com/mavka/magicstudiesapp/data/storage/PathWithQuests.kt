package com.mavka.magicstudiesapp.data.storage

import androidx.room.Embedded
import androidx.room.Relation

data class PathWithQuests(
    @Embedded val path: PathEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "path_id"
    )
    val quests: List<QuestEntity>
)