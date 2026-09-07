package com.mavka.magicstudiesapp.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [PathEntity::class, QuestEntity::class],
    version = 5,
    exportSchema = false
)
@TypeConverters(PriorityConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pathDao(): PathDao

}