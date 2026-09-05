package com.mavka.magicstudiesapp.data.mapper

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.mavka.magicstudiesapp.data.storage.QuestEntity
import com.mavka.magicstudiesapp.data.storage.QuestWithSubQuests
import com.mavka.magicstudiesapp.data.storage.SubQuestEntity
import com.mavka.magicstudiesapp.domain.models.QuestModel
import com.mavka.magicstudiesapp.domain.models.SubQuest


fun QuestWithSubQuests.toDomain(mapIcon: (Int) -> Int): QuestModel {
    return QuestModel(
        id = this.quest.id,
        title = this.quest.title,
        icon = mapIcon(this.quest.icon),
        color = Color(this.quest.color),
        subQuests = this.subQuests.map { it.toDomain() }
    )
}

fun SubQuestEntity.toDomain(): SubQuest {
    return SubQuest(
        id = this.id,
        name = this.name,
        isDone = this.isDone,
        plannedTime = this.plannedTime,
        priority = this.priority
    )
}

fun SubQuest.toEntity(questId: Int): SubQuestEntity {
    return SubQuestEntity(
        id = this.id,
        questId = questId,
        name = this.name,
        isDone = this.isDone,
        plannedTime = this.plannedTime,
        priority = this.priority
    )
}

fun QuestModel.toEntity(mapIconToId: (Int) -> Int): QuestEntity {
    return QuestEntity(
        id = this.id,
        title = this.title,
        icon = mapIconToId(this.icon),
        color = this.color.toArgb()
    )
}