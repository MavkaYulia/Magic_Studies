package com.mavka.magicstudiesapp.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PathDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPath(path: PathEntity)

    @Transaction
    @Query("SELECT * FROM paths")
    fun getAllPathsWithQuests(): Flow<List<PathWithQuests>>

    @Transaction
    @Query("SELECT * FROM paths WHERE id = :pathId")
    fun getPath(pathId: Int): Flow<PathWithQuests?>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addQuest(quest: QuestEntity)

    @Query("DELETE FROM quests WHERE id = :questId")
    suspend fun deleteQuest(questId: Int)

    @Update
    suspend fun updateQuest(quest: QuestEntity)

    @Query("DELETE FROM paths WHERE id = :pathId")
    suspend fun deletePath(pathId: Int)

}