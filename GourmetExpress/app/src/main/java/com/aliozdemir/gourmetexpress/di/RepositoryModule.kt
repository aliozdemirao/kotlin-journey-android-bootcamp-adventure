package com.aliozdemir.gourmetexpress.di

import com.aliozdemir.gourmetexpress.data.repository.FoodRepositoryImpl
import com.aliozdemir.gourmetexpress.domain.repository.FoodRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindFoodRepository(foodRepositoryImpl: FoodRepositoryImpl): FoodRepository

}