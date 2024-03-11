package com.aliozdemir.taskmaster.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aliozdemir.taskmaster.databinding.FragmentDetailBinding
import com.aliozdemir.taskmaster.ui.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: DetailViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        val bundle: DetailFragmentArgs by navArgs()
        val incomingTask = bundle.task

        binding.editTextTaskTitle.setText(incomingTask.task_title)
        binding.editTextTaskDescription.setText(incomingTask.task_description)

        binding.buttonUpdate.setOnClickListener {
            val task_title = binding.editTextTaskTitle.text.toString()
            val task_description = binding.editTextTaskDescription.text.toString()

            viewModel.updateTask(incomingTask.task_id, task_title, task_description)

            navigateToHomeFragment()
        }

        return binding.root
    }

    private fun navigateToHomeFragment() {
        val action = DetailFragmentDirections.actionDetailFragmentToHomeFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}