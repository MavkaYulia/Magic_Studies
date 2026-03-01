package com.mavka.magicstudiesapp.domain.repository

import com.mavka.magicstudiesapp.domain.models.QuestModel
import com.mavka.magicstudiesapp.domain.models.SubQuest
import kotlinx.coroutines.flow.Flow

interface QuestRepository {

    fun getQuests(): Flow<List<QuestModel>>

    suspend fun addQuest(quest: QuestModel)

    suspend fun addSubQuest(questId: Int, subQuest: SubQuest)

    suspend fun deleteSubQuest(subQuestId: Int)
    suspend fun deleteQuest(questId: Int)
    suspend fun updateQuest(questId: Int, subQuest: SubQuest)

}