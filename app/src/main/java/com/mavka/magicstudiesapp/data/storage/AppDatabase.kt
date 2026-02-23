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

//    companion object {
//        @Volatile
//        private var INSTANCE: AppDatabase? = null
//
//        fun getDatabase(context: Context): AppDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    "magic_studies_database"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
}