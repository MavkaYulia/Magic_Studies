package com.mavka.magicstudiesapp.data.mapper

import androidx.compose.ui.graphics.vector.ImageVector
import com.mavka.magicstudiesapp.data.storage.QuestEntity
import com.mavka.magicstudiesapp.data.storage.QuestWithSubQuests
import com.mavka.magicstudiesapp.data.storage.SubQuestEntity
import com.mavka.magicstudiesapp.domain.models.QuestModel
import com.mavka.magicstudiesapp.domain.models.SubQuest


fun QuestWithSubQuests.toDomain(mapIcon: (Int) -> ImageVector): QuestModel {
    return QuestModel(
        id = this.quest.id,
        title = this.quest.title,
        icon = mapIcon(this.quest.icon),
        order = this.quest.orderNumber,
        subQuests = this.subQuests.map { it.toDomain() }
    )
}

fun SubQuestEntity.toDomain(): SubQuest {
    return SubQuest(
        id = this.id,
        name = this.name,
        isDone = this.isDone,
        plannedTime = this.plannedTime
    )
}

fun SubQuest.toEntity(questId: Int): SubQuestEntity {
    return SubQuestEntity(
        id = this.id,
        questId = questId,
        name = this.name,
        isDone = this.isDone,
        plannedTime = this.plannedTime
    )
}

fun QuestModel.toEntity(mapIconToId: (ImageVector) -> Int): QuestEntity {
    return QuestEntity(
        id = this.id,
        title = this.title,
        icon = mapIconToId(this.icon),
        orderNumber = this.order
    )
}