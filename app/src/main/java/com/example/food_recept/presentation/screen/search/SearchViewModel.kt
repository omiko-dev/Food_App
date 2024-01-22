package com.example.food_recept.presentation.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food_recept.data.common.Resource
import com.example.food_recept.domain.use_case.remote.food.FoodByNameUseCase
import com.example.food_recept.presentation.model.Food
import com.example.food_recept.presentation.screen.search.event.SearchEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getFoodByNameUseCase: FoodByNameUseCase
): ViewModel() {

    private var _foodByNameStateFlow = MutableStateFlow<Resource<List<Food>>>(Resource.Idle)
    val foodByNameStateFlow get() = _foodByNameStateFlow.asStateFlow()

    fun onEvent(event: SearchEvent){
        when(event){
            is SearchEvent.GetFoodByName -> getFoodByName(event.name)
        }
    }

    private fun getFoodByName(name: String) {
        viewModelScope.launch {
            getFoodByNameUseCase(name = name).collect {
                _foodByNameStateFlow.value = it
            }
        }
    }
}

