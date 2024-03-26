package com.aliozdemir.gourmetexpress.domain.usecase

import com.aliozdemir.gourmetexpress.domain.model.Foods
import com.aliozdemir.gourmetexpress.domain.repository.FoodRepository
import javax.inject.Inject

class GetAllFoodsFromApiUseCase @Inject constructor(private val foodRepository: FoodRepository) {
    suspend fun execute(): Foods {
        return foodRepository.getAllFoodsFromApi()
    }
}