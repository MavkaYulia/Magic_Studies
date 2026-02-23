package com.mavka.magicstudiesapp.data.storage

import androidx.room.Embedded
import androidx.room.Relation

data class QuestWithSubQuests(
    @Embedded val quest: QuestEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "quest_id"
    )
    val subQuests: List<SubQuestEntity>
)