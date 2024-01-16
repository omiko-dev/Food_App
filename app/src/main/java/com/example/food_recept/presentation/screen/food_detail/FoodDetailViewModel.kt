package com.example.food_recept.presentation.screen.food_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food_recept.data.common.Resource
import com.example.food_recept.domain.use_case.FoodDetailByIdUseCase
import com.example.food_recept.presentation.model.Food
import com.example.food_recept.presentation.screen.food_detail.event.FoodDetailEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodDetailViewModel @Inject constructor(
    private val getFoodDetailByIdUseCase: FoodDetailByIdUseCase
): ViewModel() {

    private var _foodDetailStateFlow = MutableStateFlow<Resource<Food>>(Resource.Idle)
    val foodDetailStateFlow get() = _foodDetailStateFlow.asStateFlow()


    fun onEvent(event: FoodDetailEvent){
        when(event) {
            is FoodDetailEvent.GetFoodById -> getFoodById(event.id)
        }
    }

    private fun getFoodById(id: String){
        viewModelScope.launch {
            getFoodDetailByIdUseCase(id = id).collect {
                _foodDetailStateFlow.value = it
            }
        }
    }

}