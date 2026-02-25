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
    @Query("SELECT * FROM quests ORDER BY orderNumber ASC")
    fun getAllQuestsWithSubQuests(): Flow<List<QuestWithSubQuests>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSubQuest(subQuest: SubQuestEntity)

}