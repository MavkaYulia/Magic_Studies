package com.mavka.magicstudiesapp.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [QuestEntity::class, SubQuestEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun questDao(): QuestDao

}