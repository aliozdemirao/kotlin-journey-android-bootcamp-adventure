package com.aliozdemir.taskmaster.data.repo

import com.aliozdemir.taskmaster.data.datasource.TaskDataSource
import com.aliozdemir.taskmaster.data.entity.Task

class TaskRepository(var taskDataSource: TaskDataSource) {

    suspend fun insertTask(task_title: String, task_description: String) = taskDataSource.insertTask(task_title, task_description)

    suspend fun updateTask(task_id: Int, task_title: String, task_description: String) = taskDataSource.updateTask(task_id, task_title, task_description)

    suspend fun deleteTask(task_id: Int) = taskDataSource.deleteTask(task_id)

    suspend fun getAllTasks(): List<Task> = taskDataSource.getAllTasks()

    suspend fun searchTasksByTitleAndDescription(query: String): List<Task> = taskDataSource.searchTasksByTitleAndDescription(query)

}