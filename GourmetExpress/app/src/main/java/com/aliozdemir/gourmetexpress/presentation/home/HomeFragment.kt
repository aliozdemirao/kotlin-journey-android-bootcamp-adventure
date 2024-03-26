package com.aliozdemir.gourmetexpress.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aliozdemir.gourmetexpress.R
import com.aliozdemir.gourmetexpress.databinding.FragmentHomeBinding
import com.aliozdemir.gourmetexpress.domain.model.Food
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String): Boolean {
                viewModel.searchFoods(p0)
                return true
            }

            override fun onQueryTextChange(p0: String): Boolean {
                viewModel.searchFoods(p0)
                return true
            }
        })

        binding.imageViewHomeSort.setOnClickListener { showSortMenu(it) }

    }

    private fun setupRecyclerView() {
        homeAdapter = HomeAdapter(emptyList(), this::navigateToDetail)
        binding.recyclerViewHome.apply {
            layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
            adapter = homeAdapter
        }
    }

    private fun navigateToDetail(food: Food) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(food)
        findNavController().navigate(action)
    }

    private fun observeViewModel() {
        viewModel.allFoods.observe(viewLifecycleOwner) { results ->
            homeAdapter.updateFoods(results.foods)
        }
    }

    private fun showSortMenu(view: View) {
        val popupMenu = PopupMenu(requireContext(), view)
        popupMenu.menuInflater.inflate(R.menu.sort_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_sort_price_asc -> {
                    viewModel.sortFoodsByPriceAscending()
                    true
                }

                R.id.action_sort_price_desc -> {
                    viewModel.sortFoodsByPriceDescending()
                    true
                }

                else -> false
            }
        }
        popupMenu.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}