package com.example.food_recept.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food_recept.data.remote.common.Resource
import com.example.food_recept.domain.use_case.remote.catogory.CategoryUseCase
import com.example.food_recept.domain.use_case.remote.food.FoodByCategoryUseCase
import com.example.food_recept.domain.use_case.remote.food.SingleRandomFoodUseCase
import com.example.food_recept.presentation.model.Category
import com.example.food_recept.presentation.model.Food
import com.example.food_recept.presentation.model.FoodByCategory
import com.example.food_recept.presentation.screen.home.event.HomeEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val foodByCategoryUseCase: FoodByCategoryUseCase,
    private val categoryUseCase: CategoryUseCase,
    private val singleRandomFoodUseCase: SingleRandomFoodUseCase
): ViewModel() {


    private var _singleRandomStateFlow = MutableStateFlow<Resource<Food>>(Resource.Idle)
    val singleRandomStateFlow get() = _singleRandomStateFlow.asStateFlow()

    private var _categoryStateFlow = MutableStateFlow<Resource<List<Category>>>(Resource.Idle)
    val categoryStateFlow get() = _categoryStateFlow.asStateFlow()

    private var _foodByCategoryStateFlow = MutableStateFlow<Resource<List<FoodByCategory>>>(Resource.Idle)
    val foodByCategoryStateFlow get() = _foodByCategoryStateFlow.asStateFlow()

    fun onEvent(event: HomeEvent){
        when(event){
            is HomeEvent.GetCategory -> getCategory()
            is HomeEvent.GetFoodByCategory -> getFoodByCategory(event.category)
            is HomeEvent.GetSingleRandomFood -> getSingleRandomFood()
        }
    }

    private fun getCategory(){
        viewModelScope.launch {
            categoryUseCase().collect {
                _categoryStateFlow.value = it
            }
        }
    }

    private fun getFoodByCategory(category: String){
        viewModelScope.launch {
            foodByCategoryUseCase(category).collect {
                _foodByCategoryStateFlow.value = it
            }
        }
    }

    private fun getSingleRandomFood(){
        viewModelScope.launch {
            singleRandomFoodUseCase().collect {
                _singleRandomStateFlow.value = it
            }
        }
    }
}

