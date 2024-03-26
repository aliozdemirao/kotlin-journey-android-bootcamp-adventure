package com.aliozdemir.gourmetexpress.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliozdemir.gourmetexpress.domain.model.Food
import com.aliozdemir.gourmetexpress.domain.usecase.AddToCartUseCase
import com.aliozdemir.gourmetexpress.domain.usecase.InsertFoodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val addToCartUseCase: AddToCartUseCase,
    private val insertFoodUseCase: InsertFoodUseCase
) : ViewModel() {

    fun addToCart(name: String, imageName: String, price: Int, quantity: Int, username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            addToCartUseCase.execute(name, imageName, price, quantity, username)
        }
    }

    fun insertFood(food: Food) {
        viewModelScope.launch {
            insertFoodUseCase.execute(food)
        }
    }

}