package com.aliozdemir.gourmetexpress.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.aliozdemir.gourmetexpress.data.local.dao.FoodDao
import com.aliozdemir.gourmetexpress.data.mapper.toActionResult
import com.aliozdemir.gourmetexpress.data.mapper.toCartFoods
import com.aliozdemir.gourmetexpress.data.mapper.toFoodEntity
import com.aliozdemir.gourmetexpress.data.mapper.toFoodList
import com.aliozdemir.gourmetexpress.data.mapper.toFoods
import com.aliozdemir.gourmetexpress.data.remote.api.FoodApi
import com.aliozdemir.gourmetexpress.domain.model.ActionResult
import com.aliozdemir.gourmetexpress.domain.model.CartFoods
import com.aliozdemir.gourmetexpress.domain.model.Food
import com.aliozdemir.gourmetexpress.domain.model.Foods
import com.aliozdemir.gourmetexpress.domain.repository.FoodRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodRepositoryImpl @Inject constructor(
    private val foodApi: FoodApi,
    private val foodDao: FoodDao
) : FoodRepository {
    override suspend fun getAllFoodsFromApi(): Foods {
        return foodApi.getAllFoodsFromApi().toFoods()
    }

    override suspend fun getCartFoods(username: String): CartFoods {
        return foodApi.getCartFoods(username).toCartFoods()
    }

    override suspend fun addToCart(name: String, imageName: String, price: Int, quantity: Int, username: String): ActionResult {
        return foodApi.addToCart(name, imageName, price, quantity, username).toActionResult()
    }

    override suspend fun removeFromCart(id: Int, username: String): ActionResult {
        return foodApi.removeFromCart(id, username).toActionResult()
    }

    override suspend fun insertFood(food: Food) {
        return foodDao.insertFood(food.toFoodEntity())
    }

    override suspend fun deleteFood(food: Food) {
        return foodDao.deleteFood(food.toFoodEntity())
    }

    override fun getAllFoodsFromDatabase(): LiveData<List<Food>> {
        return foodDao.getAllFoodsFromDatabase().map { it.toFoodList() }
    }
}