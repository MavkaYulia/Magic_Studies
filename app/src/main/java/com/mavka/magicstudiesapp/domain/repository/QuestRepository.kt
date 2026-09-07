package com.mavka.magicstudiesapp.domain.repository

import com.mavka.magicstudiesapp.domain.models.PathModel
import com.mavka.magicstudiesapp.domain.models.SubQuest
import kotlinx.coroutines.flow.Flow

interface QuestRepository {

    fun getQuests(): Flow<List<PathModel>>
    fun getQuest(questId: Int): Flow<PathModel?>

    suspend fun addQuest(quest: PathModel)

    suspend fun addSubQuest(questId: Int, subQuest: SubQuest)

    suspend fun deleteSubQuest(subQuestId: Int)
    suspend fun deleteQuest(questId: Int)
    suspend fun updateQuest(questId: Int, subQuest: SubQuest)

    // suspend fun dayCompleted(data:Long): Flow<SubQuest?>

}