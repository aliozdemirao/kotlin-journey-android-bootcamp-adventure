package com.aliozdemir.gourmetexpress.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CartFoodDTO(
    @SerializedName("sepet_yemek_id")
    val id: String,
    @SerializedName("yemek_adi")
    val name: String,
    @SerializedName("yemek_resim_adi")
    val imageName: String,
    @SerializedName("yemek_fiyat")
    val price: String,
    @SerializedName("yemek_siparis_adet")
    val quantity: String,
    @SerializedName("kullanici_adi")
    val username: String
)