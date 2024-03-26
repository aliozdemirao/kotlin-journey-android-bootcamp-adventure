package com.aliozdemir.gourmetexpress.data.mapper

import com.aliozdemir.gourmetexpress.data.local.entity.FoodEntity
import com.aliozdemir.gourmetexpress.domain.model.Food

fun FoodEntity.toFood(): Food = Food(
    id, name, imageName, price
)

fun Food.toFoodEntity(): FoodEntity = FoodEntity(
    id, name, imageName, price
)

fun List<FoodEntity>.toFoodList(): List<Food> {
    return this.map { it.toFood() }
}