package com.aliozdemir.gourmetexpress.presentation.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliozdemir.gourmetexpress.domain.model.Food
import com.aliozdemir.gourmetexpress.domain.usecase.DeleteFoodUseCase
import com.aliozdemir.gourmetexpress.domain.usecase.GetAllFoodsFromDatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getAllFoodsFromDatabaseUseCase: GetAllFoodsFromDatabaseUseCase,
    private val deleteFoodUseCase: DeleteFoodUseCase
) : ViewModel() {

    private var _favoriteFoods: LiveData<List<Food>>? = null
    val favoriteFoods: LiveData<List<Food>>
        get() {
            if (_favoriteFoods == null) {
                loadFavoriteFoods()
            }
            return _favoriteFoods!!
        }

    private fun loadFavoriteFoods() {
        _favoriteFoods = getAllFoodsFromDatabaseUseCase.execute()
    }

    fun deleteFood(food: Food) {
        viewModelScope.launch {
            deleteFoodUseCase.execute(food)
        }
    }

}