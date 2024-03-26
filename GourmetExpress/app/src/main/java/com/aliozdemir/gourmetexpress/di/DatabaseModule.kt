package com.aliozdemir.gourmetexpress.di

import android.content.Context
import androidx.room.Room
import com.aliozdemir.gourmetexpress.data.local.dao.FoodDao
import com.aliozdemir.gourmetexpress.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideFoodDao(appDatabase: AppDatabase): FoodDao = appDatabase.foodDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "gourmet_express_database"
        ).build()

}