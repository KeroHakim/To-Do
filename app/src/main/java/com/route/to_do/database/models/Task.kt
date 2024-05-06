package com.route.to_do.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
@Entity
data class Task (
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null,
    @ColumnInfo(name = "Name")
    val title:String? = null,
    val description:String? = null,
    val date: Date? = null,
    val isDone: Boolean? = false,
)