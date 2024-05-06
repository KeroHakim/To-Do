package com.route.to_do.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.route.to_do.database.models.Task

@Database(entities = [Task::class], version = 1)
@TypeConverters(value = [ConverterType::class])
abstract class TaskDatabase : RoomDatabase(){
    abstract fun getTaskDao(): TasksDao
    companion object {
        private var INSTANCE : TaskDatabase? = null
        private val DATABASE_NAME = "Task DataBase"
        fun getInstance(context: Context) : TaskDatabase {
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context,TaskDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE!!
        }
    }

}