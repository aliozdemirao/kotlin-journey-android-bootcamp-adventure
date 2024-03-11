package com.aliozdemir.taskmaster.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.aliozdemir.taskmaster.data.entity.Task

@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): List<Task>

    @Query("SELECT * FROM tasks WHERE task_title LIKE '%' || :query || '%' OR task_description LIKE '%' || :query || '%'")
    suspend fun searchTasksByTitleAndDescription(query: String): List<Task>
}