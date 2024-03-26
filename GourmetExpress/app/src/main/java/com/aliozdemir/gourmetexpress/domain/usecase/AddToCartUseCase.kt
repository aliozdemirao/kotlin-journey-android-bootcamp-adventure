package com.aliozdemir.gourmetexpress.domain.usecase

import com.aliozdemir.gourmetexpress.domain.model.ActionResult
import com.aliozdemir.gourmetexpress.domain.repository.FoodRepository
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(private val foodRepository: FoodRepository) {
    suspend fun execute(name: String, imageName: String, price: Int, quantity: Int, username: String): ActionResult {
        return foodRepository.addToCart(name, imageName, price, quantity, username)
    }
}