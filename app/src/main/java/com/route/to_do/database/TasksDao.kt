package com.route.to_do.database

import androidx.core.view.WindowInsetsCompat.Type.InsetsType
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.route.to_do.database.models.Task
import java.util.Date

@Dao
interface TasksDao {
    @Insert
    fun insertTask(task : Task)

    @Delete
    fun deleteTask(task : Task)

    @Update
    fun updateTask(task : Task)
    @Query("SELECT * FROM Task")
    fun getAllTask(): List<Task>
    @Query("SELECT * FROM Task WHERE date = :dateTime")
    fun getTasksByDate(dateTime : Date): List<Task>
}
