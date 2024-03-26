package com.aliozdemir.gourmetexpress.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CartFoodsDTO(
    @SerializedName("sepet_yemekler")
    val cartFoods: List<CartFoodDTO>,
    @SerializedName("success")
    val success: Int
)