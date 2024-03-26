package com.aliozdemir.gourmetexpress.presentation.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliozdemir.gourmetexpress.domain.model.CartFood
import com.aliozdemir.gourmetexpress.domain.usecase.GetCartFoodsUseCase
import com.aliozdemir.gourmetexpress.domain.usecase.RemoveFromCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.EOFException
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartFoodsUseCase: GetCartFoodsUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase
) : ViewModel() {

    private val _cartFoods = MutableLiveData<List<CartFood>>()
    val cartFoods: LiveData<List<CartFood>> get() = _cartFoods

    fun getCartFoods(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = getCartFoodsUseCase.execute(username)
                withContext(Dispatchers.Main) {
                    _cartFoods.value = result.cartFoods
                }
            } catch (e: EOFException) {
                withContext(Dispatchers.Main) {
                    _cartFoods.value = emptyList()
                }
            }
        }
    }

    fun removeFromCart(name: String, username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val cartFoodsToRemove = _cartFoods.value?.filter { it.name == name } ?: emptyList()
            for (cartFood in cartFoodsToRemove) {
                removeFromCartUseCase.execute(cartFood.id, username)
            }
            getCartFoods(username)
        }
    }

}