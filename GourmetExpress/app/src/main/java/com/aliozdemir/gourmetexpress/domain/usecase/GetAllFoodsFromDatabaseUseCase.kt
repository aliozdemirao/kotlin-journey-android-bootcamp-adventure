package com.aliozdemir.gourmetexpress.domain.usecase

import androidx.lifecycle.LiveData
import com.aliozdemir.gourmetexpress.domain.model.Food
import com.aliozdemir.gourmetexpress.domain.repository.FoodRepository
import javax.inject.Inject

class GetAllFoodsFromDatabaseUseCase @Inject constructor(private val foodRepository: FoodRepository) {
    fun execute(): LiveData<List<Food>> {
        return foodRepository.getAllFoodsFromDatabase()
    }
}