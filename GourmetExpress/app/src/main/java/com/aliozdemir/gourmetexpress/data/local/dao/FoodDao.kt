package com.aliozdemir.gourmetexpress.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aliozdemir.gourmetexpress.data.local.entity.FoodEntity

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(foodEntity: FoodEntity)

    @Delete
    suspend fun deleteFood(foodEntity: FoodEntity)

    @Query("SELECT * FROM foods")
    fun getAllFoodsFromDatabase(): LiveData<List<FoodEntity>>

}