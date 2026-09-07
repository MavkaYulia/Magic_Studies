package com.mavka.magicstudiesapp.data.repository

import com.mavka.magicstudiesapp.data.mapper.IconMapper
import com.mavka.magicstudiesapp.data.mapper.toDomain
import com.mavka.magicstudiesapp.data.mapper.toEntity
import com.mavka.magicstudiesapp.data.storage.QuestDao
import com.mavka.magicstudiesapp.domain.models.PathModel
import com.mavka.magicstudiesapp.domain.models.SubQuest
import com.mavka.magicstudiesapp.domain.repository.QuestRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class QuestRepositoryImpl(
    private val questDao: QuestDao,
    private val mapper: IconMapper,
    private val scope: CoroutineScope
) : QuestRepository {

    private val quests: StateFlow<List<PathModel>> =
        questDao.getAllQuestsWithSubQuests()
            .map { listFromDb ->
                listFromDb.map { quest ->
                    quest.toDomain(mapper::getIconById)
                }
            }
            .stateIn(
                scope = scope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    override fun getQuests(): StateFlow<List<PathModel>> = quests

    override fun getQuest(questId: Int): Flow<PathModel?> {
        return questDao.getQuest(questId)
            .map { quest ->
                quest?.toDomain(mapper::getIconById)
            }
    }

    override suspend fun addQuest(quest: PathModel) {
        questDao.addQuest(
            quest.toEntity(mapper::getIdByIcon)
        )
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

    override suspend fun updateQuest(
        questId: Int,
        subQuest: SubQuest
    ) {
        questDao.updateSubQuest(
            subQuest.toEntity(questId)
        )
    }
}