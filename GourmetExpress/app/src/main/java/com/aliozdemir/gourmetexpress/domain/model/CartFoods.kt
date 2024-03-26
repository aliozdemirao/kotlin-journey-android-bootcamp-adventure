package com.aliozdemir.gourmetexpress.domain.model

data class CartFoods(
    val cartFoods: List<CartFood>,
    val success: Int
)