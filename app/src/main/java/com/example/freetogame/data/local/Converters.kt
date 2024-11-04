package com.example.freetogame.data.local

import androidx.room.TypeConverter
import java.util.Date

/**
 * @author Rigoberto Torres on 02/11/2024.
 * @version 0.0.1
 * @since 0.0.1
 */
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}