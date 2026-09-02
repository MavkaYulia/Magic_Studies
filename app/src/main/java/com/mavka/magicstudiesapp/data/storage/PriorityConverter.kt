package com.mavka.magicstudiesapp.data.storage

import androidx.room.TypeConverter
import com.mavka.magicstudiesapp.domain.models.Priority

class PriorityConverter {
    @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name
    }

    @TypeConverter
    fun toPriority(priorityName: String): Priority {
        return Priority.valueOf(priorityName)
    }
}
