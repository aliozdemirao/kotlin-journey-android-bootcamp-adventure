package com.aliozdemir.gourmetexpress.domain.repository

import androidx.lifecycle.LiveData
import com.aliozdemir.gourmetexpress.domain.model.ActionResult
import com.aliozdemir.gourmetexpress.domain.model.CartFoods
import com.aliozdemir.gourmetexpress.domain.model.Food
import com.aliozdemir.gourmetexpress.domain.model.Foods

interface FoodRepository {

    suspend fun getAllFoodsFromApi(): Foods

    suspend fun getCartFoods(username: String): CartFoods

    suspend fun addToCart(name: String, imageName: String, price: Int, quantity: Int, username: String): ActionResult

    suspend fun removeFromCart(id: Int, username: String): ActionResult

    suspend fun insertFood(food: Food)

    suspend fun deleteFood(food: Food)

    fun getAllFoodsFromDatabase(): LiveData<List<Food>>

}