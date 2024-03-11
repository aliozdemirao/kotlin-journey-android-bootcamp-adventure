package com.aliozdemir.taskmaster.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.aliozdemir.taskmaster.databinding.FragmentHomeBinding
import com.aliozdemir.taskmaster.ui.adapter.TaskAdapter
import com.aliozdemir.taskmaster.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: HomeViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel.taskList.observe(viewLifecycleOwner){
            val taskAdapter = TaskAdapter(requireContext(), it, viewModel)
            binding.recyclerViewTask.adapter = taskAdapter
        }

        binding.recyclerViewTask.layoutManager = LinearLayoutManager(requireContext())

        binding.floatingActionButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToRecordFragment()
            Navigation.findNavController(it).navigate(action)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String): Boolean {
                viewModel.searchTasksByTitleAndDescription(p0)
                return true
            }

            override fun onQueryTextChange(p0: String): Boolean {
                viewModel.searchTasksByTitleAndDescription(p0)
                return true
            }

        })

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        viewModel.getAllTasks()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}