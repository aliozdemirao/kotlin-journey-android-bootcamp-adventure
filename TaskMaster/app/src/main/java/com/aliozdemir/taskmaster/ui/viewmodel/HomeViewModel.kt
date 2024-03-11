package com.aliozdemir.taskmaster.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aliozdemir.taskmaster.data.entity.Task
import com.aliozdemir.taskmaster.data.repo.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(var taskRepository: TaskRepository) : ViewModel() {

    var taskList = MutableLiveData<List<Task>>()

    init {
        getAllTasks()
    }

    fun deleteTask(task_id: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            taskRepository.deleteTask(task_id)
            getAllTasks()
        }
    }

    fun getAllTasks() {
        CoroutineScope(Dispatchers.Main).launch {
            taskList.value = taskRepository.getAllTasks()
        }
    }

    fun searchTasksByTitleAndDescription(query: String) {
        CoroutineScope(Dispatchers.Main).launch {
            taskList.value = taskRepository.searchTasksByTitleAndDescription(query)
        }
    }

}