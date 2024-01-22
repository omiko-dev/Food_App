package com.example.food_recept.presentation.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food_recept.data.common.Resource
import com.example.food_recept.domain.use_case.local.database.user.GetUserUseCase
import com.example.food_recept.presentation.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
): ViewModel() {

    private var _authorizeUserStateFlow = MutableStateFlow<Resource<User?>>(Resource.Idle)
    val authorizeUserStateFlow get() = _authorizeUserStateFlow.asStateFlow()

    private var _splashNavigationStateFlow = MutableStateFlow<SplashNavigation?>(null)
    val splashNavigationStateFlow get() = _splashNavigationStateFlow.asStateFlow()

    init {
        checkAuthorize()
    }

    fun navigateToHomeFragment() {
        viewModelScope.launch {
            _splashNavigationStateFlow.emit(SplashNavigation.NavigateShareFragment)
        }
    }

    fun navigateToLogInFragment() {
        viewModelScope.launch {
            _splashNavigationStateFlow.emit(SplashNavigation.NavigateLogInFragment)
        }
    }

    private fun checkAuthorize(){
        viewModelScope.launch(Dispatchers.IO) {
            getUserUseCase().collect {
                _authorizeUserStateFlow.value = it
            }
        }
    }
}

sealed class SplashNavigation{
    data object NavigateShareFragment: SplashNavigation()
    data object NavigateLogInFragment: SplashNavigation()
}

