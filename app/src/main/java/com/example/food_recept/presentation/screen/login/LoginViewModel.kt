package com.example.food_recept.presentation.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food_recept.data.remote.common.Resource
import com.example.food_recept.domain.use_case.remote.auth.LoginUseCase
import com.example.food_recept.presentation.model.Login
import com.example.food_recept.presentation.model.Token
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private var _loginStateFlow = MutableStateFlow<Resource<Token>>(Resource.Idle)
    val loginStateFlow get() = _loginStateFlow.asStateFlow()

    fun login(login: Login) {
        viewModelScope.launch {
            loginUseCase(login = Login("string", "string")).collect {
                _loginStateFlow.update { it }
            }
        }
    }
}