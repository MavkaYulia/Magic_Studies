package com.mavka.magicstudiesapp.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [QuestEntity::class, SubQuestEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(PriorityConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun questDao(): QuestDao

}