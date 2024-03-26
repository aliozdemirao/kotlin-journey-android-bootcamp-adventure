package com.aliozdemir.gourmetexpress.domain.usecase

import com.aliozdemir.gourmetexpress.domain.model.Food
import com.aliozdemir.gourmetexpress.domain.repository.FoodRepository
import javax.inject.Inject

class DeleteFoodUseCase @Inject constructor(private val foodRepository: FoodRepository) {
    suspend fun execute(food: Food) {
        foodRepository.deleteFood(food)
    }
}