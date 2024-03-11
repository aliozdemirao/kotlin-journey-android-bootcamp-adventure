package com.aliozdemir.taskmaster.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.aliozdemir.taskmaster.databinding.FragmentRecordBinding
import com.aliozdemir.taskmaster.ui.viewmodel.RecordViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecordFragment : Fragment() {
    private lateinit var viewModel: RecordViewModel
    private var _binding: FragmentRecordBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: RecordViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecordBinding.inflate(inflater, container, false)

        binding.buttonSave.setOnClickListener {
            val task_title = binding.editTextTaskTitle.text.toString()
            val task_description = binding.editTextTaskDescription.text.toString()

            viewModel.insertTask(task_title, task_description)

            navigateToHomeFragment()
        }

        return binding.root
    }

    private fun navigateToHomeFragment() {
        val action = RecordFragmentDirections.actionRecordFragmentToHomeFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}