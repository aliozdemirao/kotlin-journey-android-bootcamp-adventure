package com.aliozdemir.gourmetexpress.presentation.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aliozdemir.gourmetexpress.R
import com.aliozdemir.gourmetexpress.databinding.ItemFavoriteBinding
import com.aliozdemir.gourmetexpress.domain.model.Food
import com.aliozdemir.gourmetexpress.util.BindingUtils

class FavoriteAdapter(
    private val onDeleteClick: (Food) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private var favoriteFoods: List<Food> = listOf()

    inner class FavoriteViewHolder(private val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(food: Food) {
            binding.apply {
                textViewFavoriteFoodName.text = food.name
                textViewFavoriteFoodPrice.text = itemView.context.getString(R.string.currency_symbol, food.price)

                BindingUtils.bindImage(binding.imageViewFavoriteFoodImage, food.imageName)

                imageViewFavoriteImage.setOnClickListener { onDeleteClick(favoriteFoods[adapterPosition]) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favoriteFood = favoriteFoods[position]
        holder.bind(favoriteFood)
    }

    override fun getItemCount(): Int = favoriteFoods.size

    fun updateFavoriteFoods(newFavoriteFoods: List<Food>) {
        favoriteFoods = newFavoriteFoods
        notifyDataSetChanged()
    }
}