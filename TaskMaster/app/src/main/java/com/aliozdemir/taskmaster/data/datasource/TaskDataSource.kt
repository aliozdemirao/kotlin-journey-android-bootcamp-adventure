package com.aliozdemir.taskmaster.data.datasource

import com.aliozdemir.taskmaster.data.entity.Task
import com.aliozdemir.taskmaster.room.TaskDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskDataSource(var taskDao: TaskDao) {

    suspend fun insertTask(task_title: String, task_description: String) {
        val newTask = Task(0, task_title, task_description)
        taskDao.insertTask(newTask)
    }

    suspend fun updateTask(task_id: Int, task_title: String, task_description: String) {
        val updatedTask = Task(task_id, task_title, task_description)
        taskDao.updateTask(updatedTask)
    }

    suspend fun deleteTask(task_id: Int) {
        val deletedTask = Task(task_id, "", "")
        taskDao.deleteTask(deletedTask)
    }

    suspend fun getAllTasks(): List<Task> = withContext(Dispatchers.IO) {
        return@withContext taskDao.getAllTasks()
    }

    suspend fun searchTasksByTitleAndDescription(query: String): List<Task> =
        withContext(Dispatchers.IO) {
            return@withContext taskDao.searchTasksByTitleAndDescription(query)
        }

}