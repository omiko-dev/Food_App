package com.example.food_recept.presentation.screen.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food_recept.data.common.Resource
import com.example.food_recept.domain.use_case.local.database.food.GetAllFoodDBUseCase
import com.example.food_recept.presentation.model.Food
import com.example.food_recept.presentation.screen.saved.event.SavedEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val getAllFoodDBUseCase: GetAllFoodDBUseCase
): ViewModel() {

    private var _allFavoriteFoodStateFlow = MutableStateFlow<Resource<List<Food>?>>(Resource.Idle)
    val allFavoriteFoodStateFlow get() = _allFavoriteFoodStateFlow.asStateFlow()

    private fun getAllFood(){
        viewModelScope.launch {
             getAllFoodDBUseCase().collect {
                 _allFavoriteFoodStateFlow.value = it
             }
        }
    }

    fun onEvent(event: SavedEvent){
        when(event) {
            is SavedEvent.GetAllFood -> getAllFood()
        }
    }

}