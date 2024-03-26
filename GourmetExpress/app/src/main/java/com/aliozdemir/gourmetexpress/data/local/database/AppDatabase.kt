package com.aliozdemir.gourmetexpress.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aliozdemir.gourmetexpress.data.local.dao.FoodDao
import com.aliozdemir.gourmetexpress.data.local.entity.FoodEntity

@Database(entities = [FoodEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao

}