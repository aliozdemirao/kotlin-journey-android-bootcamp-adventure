package com.aliozdemir.taskmaster.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.aliozdemir.taskmaster.data.entity.Task
import com.aliozdemir.taskmaster.databinding.ItemTaskRowBinding
import com.aliozdemir.taskmaster.ui.fragment.HomeFragmentDirections
import com.aliozdemir.taskmaster.ui.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar

class TaskAdapter(var mContext: Context, var taskList: List<Task>, var viewModel: HomeViewModel): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>(){

    inner class TaskViewHolder(var binding: ItemTaskRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.TaskViewHolder {
        val binding = ItemTaskRowBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskAdapter.TaskViewHolder, position: Int) {
        val task = taskList.get(position)

        holder.binding.textViewTaskTitle.text = task.task_title
        holder.binding.textViewTaskDescription.text = task.task_description

        holder.binding.cardViewRow.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(task)
            Navigation.findNavController(it).navigate(action)
        }

        holder.binding.imageViewDelete.setOnClickListener {
            val snackbar = Snackbar.make(it, "Are you sure you want to delete ${task.task_title}?", Snackbar.LENGTH_LONG)

            snackbar.setAction("Yes") {
                viewModel.deleteTask(task.task_id)
                Snackbar.make(it, "Task deleted.", Snackbar.LENGTH_SHORT).show()
            }

            snackbar.show()
        }

    }

    override fun getItemCount(): Int {
        return taskList.size
    }

}