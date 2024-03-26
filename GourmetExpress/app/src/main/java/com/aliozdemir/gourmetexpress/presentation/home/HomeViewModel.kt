package com.aliozdemir.gourmetexpress.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliozdemir.gourmetexpress.domain.model.Food
import com.aliozdemir.gourmetexpress.domain.model.Foods
import com.aliozdemir.gourmetexpress.domain.usecase.GetAllFoodsFromApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllFoodsFromApiUseCase: GetAllFoodsFromApiUseCase
) : ViewModel() {
    private val _allFoods = MutableLiveData<Foods>()
    val allFoods: LiveData<Foods> get() = _allFoods

    private var originalFoodsList: List<Food> = emptyList()

    init {
        fetchAllFoods()
    }

    private fun fetchAllFoods() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getAllFoodsFromApiUseCase.execute()
            withContext(Dispatchers.Main) {
                _allFoods.value = result
                originalFoodsList = result.foods
            }
        }
    }

    fun searchFoods(query: String) {
        val filteredFoodsList = originalFoodsList.filter { food ->
            food.name.contains(query, ignoreCase = true)
        }
        _allFoods.value = Foods(filteredFoodsList, _allFoods.value?.success ?: -1)
    }

    fun sortFoodsByPriceAscending() {
        val sortedFoodsList = _allFoods.value?.foods?.sortedBy { it.price }
        _allFoods.value = Foods(sortedFoodsList ?: emptyList(), _allFoods.value?.success ?: -1)
    }

    fun sortFoodsByPriceDescending() {
        val sortedFoodsList = _allFoods.value?.foods?.sortedByDescending { it.price }
        _allFoods.value = Foods(sortedFoodsList ?: emptyList(), _allFoods.value?.success ?: -1)
    }

}