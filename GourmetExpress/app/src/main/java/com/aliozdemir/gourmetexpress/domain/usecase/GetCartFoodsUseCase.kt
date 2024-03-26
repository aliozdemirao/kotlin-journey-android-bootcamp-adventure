package com.aliozdemir.gourmetexpress.domain.usecase

import com.aliozdemir.gourmetexpress.domain.model.CartFoods
import com.aliozdemir.gourmetexpress.domain.repository.FoodRepository
import javax.inject.Inject

class GetCartFoodsUseCase @Inject constructor(private val foodRepository: FoodRepository) {
    suspend fun execute(username: String): CartFoods {
        return foodRepository.getCartFoods(username)
    }
}