package com.aliozdemir.gourmetexpress.domain.usecase

import com.aliozdemir.gourmetexpress.domain.model.ActionResult
import com.aliozdemir.gourmetexpress.domain.repository.FoodRepository
import javax.inject.Inject

class RemoveFromCartUseCase @Inject constructor(private val foodRepository: FoodRepository) {
    suspend fun execute(id: Int, username: String): ActionResult {
        return foodRepository.removeFromCart(id, username)
    }
}