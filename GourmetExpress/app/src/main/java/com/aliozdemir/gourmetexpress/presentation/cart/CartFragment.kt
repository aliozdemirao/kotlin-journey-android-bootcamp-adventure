package com.aliozdemir.gourmetexpress.presentation.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aliozdemir.gourmetexpress.R
import com.aliozdemir.gourmetexpress.databinding.FragmentCartBinding
import com.aliozdemir.gourmetexpress.domain.model.CartFood
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CartViewModel by viewModels()

    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        updateCartItems()
        observeViewModel()

        binding.buttonCartCheckout.setOnClickListener {
            binding.animationView.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()

        updateCartItems()
    }

    private fun updateCartItems() {
        val username = "ali_ozdemir"
        viewModel.getCartFoods(username)
    }

    private fun setupRecyclerView() {
        cartAdapter = CartAdapter(emptyList(), this::onDeleteClickListener)
        binding.recyclerViewCart.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cartAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.cartFoods.observe(viewLifecycleOwner) { cartFoods ->
            cartAdapter.updateCarts(cartFoods)
            updateTotalPrice()
        }
    }

    private fun onDeleteClickListener(cartFood: CartFood) {
        viewModel.removeFromCart(cartFood.name, cartFood.username)
        updateTotalPrice()
    }

    private fun updateTotalPrice() {
        val totalPrice = cartAdapter.calculateTotalPrice()
        binding.textViewTotal.text = getString(R.string.currency_symbol, (totalPrice))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}