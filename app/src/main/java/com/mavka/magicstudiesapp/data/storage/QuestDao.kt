package com.mavka.magicstudiesapp.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuest(quest: QuestEntity)

    @Query("SELECT * FROM quests ORDER BY orderNumber ASC")
    fun getAllQuests(): Flow<List<QuestWithSubQuests>>

}