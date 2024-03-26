package com.aliozdemir.gourmetexpress.data.remote.dto

import com.google.gson.annotations.SerializedName

data class FoodsDTO(
    @SerializedName("yemekler")
    val foods: List<FoodDTO>,
    @SerializedName("success")
    val success: Int
)