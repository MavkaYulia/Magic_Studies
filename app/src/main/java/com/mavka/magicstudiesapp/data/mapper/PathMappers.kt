package com.mavka.magicstudiesapp.data.mapper

import com.mavka.magicstudiesapp.data.storage.PathEntity
import com.mavka.magicstudiesapp.data.storage.PathWithQuests
import com.mavka.magicstudiesapp.data.storage.QuestEntity
import com.mavka.magicstudiesapp.domain.models.PathModel
import com.mavka.magicstudiesapp.domain.models.QuestModel


fun PathWithQuests.toDomain(mapIcon: (Int) -> Int): PathModel {
    return PathModel(
        id = this.path.id,
        title = this.path.title,
        icon = mapIcon(this.path.icon),
        color = this.path.color,
        quests = this.quests.map { it.toDomain() }
    )
}

fun QuestEntity.toDomain(): QuestModel {
    return QuestModel(
        id = this.id,
        name = this.name,
        isDone = this.isDone,
        plannedTime = this.plannedTime,
        priority = this.priority
    )
}

fun QuestModel.toEntity(pathId: Int): QuestEntity {
    return QuestEntity(
        id = this.id,
        pathId = pathId,
        name = this.name,
        isDone = this.isDone,
        plannedTime = this.plannedTime,
        priority = this.priority
    )
}

fun PathModel.toEntity(mapIconToId: (Int) -> Int): PathEntity {
    return PathEntity(
        id = this.id,
        title = this.title,
        icon = mapIconToId(this.icon),
        color = this.color
    )
}