package com.mavka.magicstudiesapp.data.repository

import com.mavka.magicstudiesapp.data.mapper.IconMapper
import com.mavka.magicstudiesapp.data.mapper.toDomain
import com.mavka.magicstudiesapp.data.mapper.toEntity
import com.mavka.magicstudiesapp.data.storage.QuestDao
import com.mavka.magicstudiesapp.domain.models.QuestModel
import com.mavka.magicstudiesapp.domain.repository.QuestRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuestRepositoryImpl(
    private val questDao: QuestDao,
    private val mapper: IconMapper
) : QuestRepository {

    override fun getQuests(): Flow<List<QuestModel>> {
        return questDao.getAllQuests().map { listFromDb ->
            listFromDb.map { quest ->
                quest.toDomain(mapper::getVectorById)
            }
        }
    }

    override suspend fun addQuest(quest: QuestModel) {
        questDao.addQuest(quest.toEntity(mapper::getIdByVector))
    }

/*    override fun deleteQuest(quest: QuestModel) {
        questDao.deleteQuest(quest.toEntity(mapper::getIdByVector))
    }*/

  /*  override fun updateQuest(quest: QuestModel) {
        questDao.updateQuest(quest.toEntity(mapper::getIdByVector))
    }*/

}