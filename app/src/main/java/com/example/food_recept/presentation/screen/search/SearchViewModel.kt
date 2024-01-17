package com.example.food_recept.presentation.screen.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food_recept.data.remote.common.Resource
import com.example.food_recept.data.remote.common.resourceMapper
import com.example.food_recept.domain.use_case.remote.catogory.CategoryUseCase
import com.example.food_recept.domain.use_case.CountryUseCase
import com.example.food_recept.domain.use_case.remote.food.FoodByNameUseCase
import com.example.food_recept.presentation.model.Category
import com.example.food_recept.presentation.model.Country
import com.example.food_recept.presentation.model.Food
import com.example.food_recept.presentation.screen.search.event.SearchEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getFoodByNameUseCase: FoodByNameUseCase,
    private val getCategoryUseCase: CategoryUseCase,
    private val getCountryUseCase: CountryUseCase
): ViewModel() {

    private var _foodByNameStateFlow = MutableStateFlow<Resource<List<Food>>>(Resource.Idle)
    val foodByNameStateFlow get() = _foodByNameStateFlow.asStateFlow()

    private var _categoryStateFlow = MutableStateFlow<Resource<List<Category>>>(Resource.Idle)
    val categoryStateFlow get() = _categoryStateFlow.asStateFlow()

    private var _countryStateFlow = MutableStateFlow<Resource<List<Country>>>(Resource.Idle)
    val countryStateFlow get() = _countryStateFlow.asStateFlow()

    fun onEvent(event: SearchEvent){
        when(event){
            is SearchEvent.GetFoodByName -> getFoodByName(event.name)
            is SearchEvent.GetCategoryList -> getCategory()
            is SearchEvent.GetCountryList -> getCountry()
        }
    }

    private fun getFoodByName(name: String, category: String? = null, country: String? = null) {
        viewModelScope.launch {
            getFoodByNameUseCase(name = name).collect {
                _foodByNameStateFlow.value = it
            }
        }
    }

    private fun getCategory(){
        viewModelScope.launch {
            getCategoryUseCase().collect {
                _categoryStateFlow.value = it
            }
        }
    }

    fun getFilteredFood(category: String?, country: String?) {
        viewModelScope.launch {
            Log.i("omiko", "$category, $country")
            _foodByNameStateFlow.value = _foodByNameStateFlow.value.resourceMapper {
                it.filter { food ->
                    food.country.contains(country.orEmpty()) && food.category.contains(category.orEmpty())
                }
            }
        }
    }

    private fun getCountry(){
        viewModelScope.launch {
            getCountryUseCase().collect {
                _countryStateFlow.value = it
            }
        }
    }
}

