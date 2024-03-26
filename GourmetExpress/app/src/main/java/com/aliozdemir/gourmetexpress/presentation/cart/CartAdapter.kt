package com.aliozdemir.gourmetexpress.presentation.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aliozdemir.gourmetexpress.R
import com.aliozdemir.gourmetexpress.databinding.ItemCartBinding
import com.aliozdemir.gourmetexpress.domain.model.CartFood
import com.aliozdemir.gourmetexpress.util.BindingUtils

class CartAdapter(
    private var cartFoods: List<CartFood>,
    private val onDeleteClickListener: (CartFood) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(private val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cartFood: CartFood) {
            binding.apply {
                textViewCartFoodName.text = cartFood.name
                textViewCartFoodPrice.text =
                    itemView.context.getString(R.string.currency_symbol, cartFood.price)
                textViewCartQuantity.text = cartFood.quantity.toString()
                textViewCartTotal.text = itemView.context.getString(
                    R.string.currency_symbol,
                    (cartFood.quantity * cartFood.price)
                )

                BindingUtils.bindImage(binding.imageViewCartFoodImage, cartFood.imageName)

                imageViewCartDelete.setOnClickListener { onDeleteClickListener(cartFood) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartFood = cartFoods[position]
        holder.bind(cartFood)
    }

    override fun getItemCount(): Int {
        return cartFoods.size
    }

    fun updateCarts(newCartFoods: List<CartFood>) {
        val groupedCartFoods = mutableListOf<CartFood>()

        for (cartFood in newCartFoods) {
            val existingCartItem = groupedCartFoods.find { it.name == cartFood.name }
            if (existingCartItem != null) {
                existingCartItem.quantity += cartFood.quantity
            } else {
                groupedCartFoods.add(cartFood)
            }
        }

        cartFoods = groupedCartFoods
        notifyDataSetChanged()
    }

    fun calculateTotalPrice(): Int {
        var totalPrice = 10
        for (cartFood in cartFoods) {
            totalPrice += cartFood.quantity * cartFood.price
        }
        return totalPrice
    }
}