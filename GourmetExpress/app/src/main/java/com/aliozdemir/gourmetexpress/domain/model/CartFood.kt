package com.aliozdemir.gourmetexpress.domain.model

data class CartFood(
    val id: Int,
    val name: String,
    val imageName: String,
    val price: Int,
    var quantity: Int,
    val username: String
)