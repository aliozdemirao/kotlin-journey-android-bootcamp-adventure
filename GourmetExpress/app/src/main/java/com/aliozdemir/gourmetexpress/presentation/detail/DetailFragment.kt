package com.aliozdemir.gourmetexpress.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aliozdemir.gourmetexpress.R
import com.aliozdemir.gourmetexpress.databinding.FragmentDetailBinding
import com.aliozdemir.gourmetexpress.domain.model.Food
import com.aliozdemir.gourmetexpress.util.BindingUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    private lateinit var currentFood: Food

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentFood = args.food

        binding.apply {
            textViewDetailFoodName.text = currentFood.name
            textViewDetailFoodPrice.text = getString(R.string.currency_symbol, currentFood.price)
            textViewDetailTotal.text = getString(R.string.currency_symbol, (1 * currentFood.price))

            BindingUtils.bindImage(binding.imageViewDetailFoodImage, currentFood.imageName)

            imageViewIncrease.setOnClickListener { updateQuantity(1) }
            imageViewDecrease.setOnClickListener { updateQuantity(-1) }

            buttonAddToCart.setOnClickListener {
                addToCart()
                navigateToCart()
            }

            ratingBar.rating = 5.toFloat()

            imageViewDetailFavorite.setOnClickListener {
                insertFood()
                binding.imageViewDetailFavorite.setColorFilter(ContextCompat.getColor(requireContext(), R.color.red))
            }

        }

    }

    private fun updateQuantity(delta: Int) {
        val currentQuantity = binding.textViewDetailQuantity.text.toString().toInt()
        val newQuantity = currentQuantity + delta
        if (newQuantity >= 1) {
            binding.textViewDetailQuantity.text = newQuantity.toString()
            binding.textViewDetailTotal.text = getString(R.string.currency_symbol, (newQuantity * currentFood.price))
        }
    }

    private fun addToCart() {
        val quantity = binding.textViewDetailQuantity.text.toString().toInt()
        val username = "ali_ozdemir"
        viewModel.addToCart(currentFood.name, currentFood.imageName, currentFood.price, quantity, username)
    }

    private fun navigateToCart() {
        val action = DetailFragmentDirections.actionDetailFragmentToCartFragment()
        findNavController().navigate(action)
    }

    private fun insertFood() {
        viewModel.insertFood(currentFood)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}