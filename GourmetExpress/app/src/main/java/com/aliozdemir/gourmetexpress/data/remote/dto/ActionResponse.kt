package com.aliozdemir.gourmetexpress.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ActionResponse(
    @SerializedName("success")
    val success: Int,
    @SerializedName("message")
    val message: String
)