package com.mavka.magicstudiesapp.data.repository

import com.mavka.magicstudiesapp.data.mapper.IconMapper
import com.mavka.magicstudiesapp.data.mapper.toDomain
import com.mavka.magicstudiesapp.data.mapper.toEntity
import com.mavka.magicstudiesapp.data.storage.QuestDao
import com.mavka.magicstudiesapp.domain.models.QuestModel
import com.mavka.magicstudiesapp.domain.models.SubQuest
import com.mavka.magicstudiesapp.domain.repository.QuestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class QuestRepositoryImpl(
    private val questDao: QuestDao,
    private val mapper: IconMapper
) : QuestRepository {

    override fun getQuests(): Flow<List<QuestModel>> {
        return questDao.getAllQuestsWithSubQuests()
            .map { listFromDb ->
                listFromDb.map { quest ->
                    quest.toDomain(mapper::getVectorById)
                }
            }.flowOn(Dispatchers.IO)
    }

    override suspend fun addQuest(quest: QuestModel) {
        questDao.addQuest(quest.toEntity(mapper::getIdByVector))
    }

    override suspend fun addSubQuest(
        questId: Int,
        subQuest: SubQuest
    ) {
        questDao.addSubQuest(subQuest.toEntity(questId))
    }

    override suspend fun deleteSubQuest(subQuestId: Int) {
        questDao.deleteSubQuest(subQuestId)
    }

    override suspend fun deleteQuest(questId: Int) {
        questDao.deleteQuest(questId)
    }

    override suspend fun updateQuest(questId: Int, subQuest: SubQuest) {
        questDao.updateSubQuest(subQuest.toEntity(questId))
    }

}