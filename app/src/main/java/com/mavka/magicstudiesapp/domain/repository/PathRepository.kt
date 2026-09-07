package com.mavka.magicstudiesapp.domain.repository

import com.mavka.magicstudiesapp.domain.models.PathModel
import com.mavka.magicstudiesapp.domain.models.Quest
import kotlinx.coroutines.flow.Flow

interface PathRepository {

    fun getPaths(): Flow<List<PathModel>>
    fun getPath(pathId: Int): Flow<PathModel?>

    suspend fun addPath(path: PathModel)

    suspend fun addQuest(pathId: Int, quest: Quest)

    suspend fun deleteQuest(questId: Int)
    suspend fun deletePath(pathId: Int)
    suspend fun updateQuest(pathId: Int, quest: Quest)

    // suspend fun dayCompleted(data:Long): Flow<Quest?>

}