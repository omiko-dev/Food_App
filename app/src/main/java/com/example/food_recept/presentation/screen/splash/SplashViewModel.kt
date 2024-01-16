package com.example.food_recept.presentation.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel: ViewModel() {

    private var _splashNavigationStateFlow = MutableStateFlow<SplashNavigation?>(null)
    val splashNavigationStateFlow get() = _splashNavigationStateFlow.asStateFlow()

    private fun navigation(){
        viewModelScope.launch {
            _splashNavigationStateFlow.update {
                SplashNavigation.NavigateHomeFragment
            }
        }
    }





}

sealed class SplashNavigation{
    data object NavigateStartFragment: SplashNavigation()
    data object NavigateHomeFragment: SplashNavigation()
    data object NavigateLogInFragment: SplashNavigation()
}

