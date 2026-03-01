package com.mavka.magicstudiesapp.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuest(quest: QuestEntity)

    @Transaction
    @Query("SELECT * FROM quests")
    fun getAllQuestsWithSubQuests(): Flow<List<QuestWithSubQuests>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSubQuest(subQuest: SubQuestEntity)

    @Query("DELETE FROM sub_quests WHERE id = :subQuestId")
    suspend fun deleteSubQuest(subQuestId: Int)
    @Query("DELETE FROM quests WHERE id = :questId")
    suspend fun deleteQuest(questId: Int)

}