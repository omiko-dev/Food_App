package com.example.food_recept.presentation.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food_recept.data.common.Resource
import com.example.food_recept.domain.use_case.local.database.user.InsertUserUseCase
import com.example.food_recept.domain.use_case.remote.auth.LoginUseCase
import com.example.food_recept.domain.use_case.remote.user.GetUserFoodUseCase
import com.example.food_recept.presentation.model.Login
import com.example.food_recept.presentation.model.User
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val insertUserUseCase: InsertUserUseCase,
    private val getUserFoodUseCase: GetUserFoodUseCase
) : ViewModel() {
    private var _loginStateFlow = MutableStateFlow<Resource<FirebaseUser>>(Resource.Idle)
    val loginStateFlow get() = _loginStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            getUserFoodUseCase()
        }
    }

    fun login(login: Login) {
        viewModelScope.launch {
            loginUseCase(login = Login(login.email, login.password)).collect {
                _loginStateFlow.value = it
            }
        }
    }

    fun insertUserInDB(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            insertUserUseCase(user)
        }
    }
}