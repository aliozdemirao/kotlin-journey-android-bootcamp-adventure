package com.aliozdemir.gourmetexpress.data.remote.api

import com.aliozdemir.gourmetexpress.data.remote.dto.ActionResponse
import com.aliozdemir.gourmetexpress.data.remote.dto.CartFoodsDTO
import com.aliozdemir.gourmetexpress.data.remote.dto.FoodsDTO
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodApi {

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun getAllFoodsFromApi(): FoodsDTO

    @FormUrlEncoded
    @POST("yemekler/sepettekiYemekleriGetir.php")
    suspend fun getCartFoods(
        @Field("kullanici_adi") username: String
    ): CartFoodsDTO

    @FormUrlEncoded
    @POST("yemekler/sepeteYemekEkle.php")
    suspend fun addToCart(
        @Field("yemek_adi") name: String,
        @Field("yemek_resim_adi") imageName: String,
        @Field("yemek_fiyat") price: Int,
        @Field("yemek_siparis_adet") quantity: Int,
        @Field("kullanici_adi") username: String
    ): ActionResponse

    @FormUrlEncoded
    @POST("yemekler/sepettenYemekSil.php")
    suspend fun removeFromCart(
        @Field("sepet_yemek_id") id: Int,
        @Field("kullanici_adi") username: String
    ): ActionResponse

}