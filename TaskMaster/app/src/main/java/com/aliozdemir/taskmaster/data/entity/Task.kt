package com.aliozdemir.taskmaster.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity("tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id")
    @NotNull
    var task_id: Int,

    @ColumnInfo(name = "task_title")
    @NotNull
    var task_title: String,

    @ColumnInfo(name = "task_description")
    @NotNull
    var task_description: String
) : Serializable