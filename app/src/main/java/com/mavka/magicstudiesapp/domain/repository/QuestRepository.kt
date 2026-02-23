package com.mavka.magicstudiesapp.domain.repository

import com.mavka.magicstudiesapp.domain.models.QuestModel
import kotlinx.coroutines.flow.Flow

interface QuestRepository {

    fun getQuests(): Flow<List<QuestModel>>

    suspend fun addQuest(quest: QuestModel)

}