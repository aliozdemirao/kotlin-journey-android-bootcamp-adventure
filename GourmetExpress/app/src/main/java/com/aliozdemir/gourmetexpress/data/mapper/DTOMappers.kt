package com.aliozdemir.gourmetexpress.data.mapper

import com.aliozdemir.gourmetexpress.data.remote.dto.ActionResponse
import com.aliozdemir.gourmetexpress.data.remote.dto.CartFoodDTO
import com.aliozdemir.gourmetexpress.data.remote.dto.CartFoodsDTO
import com.aliozdemir.gourmetexpress.data.remote.dto.FoodDTO
import com.aliozdemir.gourmetexpress.data.remote.dto.FoodsDTO
import com.aliozdemir.gourmetexpress.domain.model.ActionResult
import com.aliozdemir.gourmetexpress.domain.model.CartFood
import com.aliozdemir.gourmetexpress.domain.model.CartFoods
import com.aliozdemir.gourmetexpress.domain.model.Food
import com.aliozdemir.gourmetexpress.domain.model.Foods

fun FoodDTO.toFood(): Food = Food(
    id = id.toInt(),
    name = name,
    imageName = imageName,
    price = price.toInt()
)

fun FoodsDTO.toFoods(): Foods = Foods(
    foods = foods.map { it.toFood() },
    success
)

fun CartFoodDTO.toCartFood(): CartFood = CartFood(
    id = id.toInt(),
    name = name,
    imageName = imageName,
    price = price.toInt(),
    quantity = quantity.toInt(),
    username = username
)

fun CartFoodsDTO.toCartFoods(): CartFoods = CartFoods(
    cartFoods = cartFoods.map { it.toCartFood() },
    success = success
)

fun ActionResponse.toActionResult(): ActionResult = ActionResult(
    success, message
)