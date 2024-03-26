package com.aliozdemir.gourmetexpress.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aliozdemir.gourmetexpress.R
import com.aliozdemir.gourmetexpress.databinding.ItemFoodBinding
import com.aliozdemir.gourmetexpress.domain.model.Food
import com.aliozdemir.gourmetexpress.util.BindingUtils

class HomeAdapter(
    private var foods: List<Food>,
    private val onFoodClicked: (food: Food) -> Unit
) : RecyclerView.Adapter<HomeAdapter.FoodViewHolder>() {

    inner class FoodViewHolder(private val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(food: Food) {
            binding.apply {
                textViewHomeFoodName.text = food.name
                textViewHomeFoodPrice.text = itemView.context.getString(R.string.currency_symbol, food.price)

                BindingUtils.bindImage(binding.imageViewHomeFoodImage, food.imageName)

                binding.imageViewHomeFoodImage.setOnClickListener { onFoodClicked(food) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.FoodViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeAdapter.FoodViewHolder, position: Int) {
        val food = foods[position]
        holder.bind(food)
    }

    override fun getItemCount(): Int {
        return foods.size
    }

    fun updateFoods(newFoods: List<Food>) {
        foods = newFoods
        notifyDataSetChanged()
    }

}