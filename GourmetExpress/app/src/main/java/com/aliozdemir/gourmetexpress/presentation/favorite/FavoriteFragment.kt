package com.aliozdemir.gourmetexpress.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aliozdemir.gourmetexpress.databinding.FragmentFavoriteBinding
import com.aliozdemir.gourmetexpress.domain.model.Food
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoriteViewModel by viewModels()
    private lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()

    }

    private fun setupRecyclerView() {
        favoriteAdapter = FavoriteAdapter(this::deleteFavoriteFood)
        binding.recyclerViewFavorite.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoriteAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.favoriteFoods.observe(viewLifecycleOwner) { favoriteFoods ->
            favoriteAdapter.updateFavoriteFoods(favoriteFoods)
        }
    }

    private fun deleteFavoriteFood(favoriteFood: Food) {
        viewModel.deleteFood(favoriteFood)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}