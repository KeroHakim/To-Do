package com.route.to_do.database

import androidx.room.TypeConverter
import java.util.Date

class ConverterType {
    @TypeConverter
    fun convertToDate(dateTime : Long) : Date{
        return Date(dateTime)
    }
    @TypeConverter
    fun converterFromDate(date: Date) : Long{
        return date.time
    }
}