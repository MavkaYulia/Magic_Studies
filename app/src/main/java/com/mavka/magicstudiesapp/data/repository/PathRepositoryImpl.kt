package com.mavka.magicstudiesapp.data.repository

import com.mavka.magicstudiesapp.data.mapper.IconMapper
import com.mavka.magicstudiesapp.data.mapper.toDomain
import com.mavka.magicstudiesapp.data.mapper.toEntity
import com.mavka.magicstudiesapp.data.storage.PathDao
import com.mavka.magicstudiesapp.domain.models.PathModel
import com.mavka.magicstudiesapp.domain.models.Quest
import com.mavka.magicstudiesapp.domain.repository.PathRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class PathRepositoryImpl(
    private val pathDao: PathDao,
    private val mapper: IconMapper,
    private val scope: CoroutineScope
) : PathRepository {

    private val paths: StateFlow<List<PathModel>> =
        pathDao.getAllPathsWithQuests()
            .map { listFromDb ->
                listFromDb.map { path ->
                    path.toDomain(mapper::getIconById)
                }
            }
            .stateIn(
                scope = scope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    override fun getPaths(): StateFlow<List<PathModel>> = paths

    override fun getPath(pathId: Int): Flow<PathModel?> {
        return paths
            .map { pathList ->
                pathList.find { it.id == pathId }
            }
            .distinctUntilChanged()
    }

    override suspend fun addPath(path: PathModel) {
        pathDao.addPath(
            path.toEntity(mapper::getIdByIcon)
        )
    }

    override suspend fun addQuest(
        pathId: Int,
        quest: Quest
    ) {
        pathDao.addQuest(quest.toEntity(pathId))
    }

    override suspend fun deleteQuest(questId: Int) {
        pathDao.deleteQuest(questId)
    }

    override suspend fun deletePath(pathId: Int) {
        pathDao.deletePath(pathId)
    }

    override suspend fun updateQuest(
        pathId: Int,
        quest: Quest
    ) {
        pathDao.updateQuest(
            quest.toEntity(pathId)
        )
    }
}