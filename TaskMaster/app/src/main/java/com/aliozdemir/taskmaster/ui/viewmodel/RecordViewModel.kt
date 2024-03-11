package com.aliozdemir.taskmaster.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.aliozdemir.taskmaster.data.repo.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(var taskRepository: TaskRepository) : ViewModel() {

    fun insertTask(task_title: String, task_description: String) {
        CoroutineScope(Dispatchers.Main).launch {
            taskRepository.insertTask(task_title, task_description)
        }
    }

}