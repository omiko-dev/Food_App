package com.example.food_recept.presentation.screen.food_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food_recept.data.common.Resource
import com.example.food_recept.domain.use_case.local.database.food.DeleteFoodByIdDBUseCase
import com.example.food_recept.domain.use_case.local.database.food.GetAllFoodDBUseCase
import com.example.food_recept.domain.use_case.local.database.food.InsertFoodDBUseCase
import com.example.food_recept.domain.use_case.remote.food.FoodDetailByIdUseCase
import com.example.food_recept.domain.use_case.remote.user.AddUserFoodUseCase
import com.example.food_recept.domain.use_case.remote.user.DeleteUserFoodUseCase
import com.example.food_recept.presentation.model.Food
import com.example.food_recept.presentation.screen.food_detail.event.FoodDetailEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodDetailViewModel @Inject constructor(
    private val getFoodDetailByIdUseCase: FoodDetailByIdUseCase,
    private val insertFoodDBUseCase: InsertFoodDBUseCase,
    private val addUserFoodUseCase: AddUserFoodUseCase,
    private val getAllFoodDBUseCase: GetAllFoodDBUseCase,
    private val deleteFoodByIdDBUseCase: DeleteFoodByIdDBUseCase,
    private val deleteUserFoodUseCase: DeleteUserFoodUseCase
) : ViewModel() {

    private var _foodDetailStateFlow = MutableStateFlow<Resource<Food>>(Resource.Idle)
    val foodDetailStateFlow get() = _foodDetailStateFlow.asStateFlow()


    fun onEvent(event: FoodDetailEvent) {
        when (event) {
            is FoodDetailEvent.GetFoodById -> getFoodById(event.id)
            is FoodDetailEvent.AddFavorite -> insertFood(event.food)
        }
    }

    private fun insertFood(food: Food) {
        viewModelScope.launch(Dispatchers.IO) {
            getAllFoodDBUseCase().collect {
                when (it) {
                    is Resource.Success -> {
                        val isFoodInDatabase = it.success?.any { existingFood ->
                            existingFood.idMeal == food.idMeal
                        }
                        if (isFoodInDatabase == true) {
                            deleteFoodByIdDBUseCase(food = food)
                            deleteUserFoodUseCase(foodId = food.idMeal)
                        } else {
                            insertFoodDBUseCase(food = food)
                            addUserFoodUseCase(food = food)
                        }
                    }

                    else -> {}
                }
            }
        }
    }

    private fun getFoodById(id: String) {
        viewModelScope.launch {
            getAllFoodDBUseCase().collect {
                when (it) {
                    is Resource.Success -> {
                        getFoodDetailByIdUseCase(id = id).collect { food ->
                            _foodDetailStateFlow.value = food
                        }
                    }

                    else -> {}
                }
            }
        }
    }
}