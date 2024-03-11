package com.aliozdemir.taskmaster.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.aliozdemir.taskmaster.data.repo.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(var taskRepository: TaskRepository) : ViewModel() {

    fun updateTask(task_id: Int, task_title: String, task_description: String) {
        CoroutineScope(Dispatchers.Main).launch {
            taskRepository.updateTask(task_id, task_title, task_description)
        }
    }

}