package com.mavka.magicstudiesapp.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestDao {
    // додавати квести з підквестами , бо зараз це тільки квести без підквестів

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuest(quest: QuestEntity)

    @Query("SELECT * FROM quests ORDER BY orderNumber ASC")
    fun getAllQuests(): Flow<List<QuestWithSubQuests>>





    /* @Query("SELECT * FROM quests WHERE id = :id")
     suspend fun getQuest(id: Int): QuestEntity?

     @Update
     fun updateQuest(quest: QuestEntity)

     @Delete
     fun deleteQuest(quest: QuestEntity)
 */
/*    @Query("DELETE FROM quests")
    suspend fun deleteAllQuests()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSubQuest(quest: SubQuestEntity): Long



    @Query("SELECT * FROM sub_quests WHERE id = :id")
    suspend fun getSubQuest(id: Int): SubQuestEntity?

    @Update
    suspend fun updateQuest(subQuest: SubQuestEntity)

    @Delete
    suspend fun deleteQuest(subQuest: SubQuestEntity)

    @Query("SELECT * FROM sub_quests")
    fun getAllSubQuests(): Flow<List<SubQuestEntity>>

    @Query("DELETE FROM sub_quests")
    suspend fun deleteAllSubQuests()*/

}