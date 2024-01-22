package com.example.food_recept.presentation.screen.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food_recept.data.common.Resource
import com.example.food_recept.domain.use_case.remote.auth.RegisterUseCase
import com.example.food_recept.presentation.model.Register
import com.example.food_recept.presentation.screen.register.event.RegisterEvent
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
): ViewModel() {
    private var _registerStateFlow = MutableStateFlow<Resource<FirebaseUser>>(Resource.Idle)
    val registerStateFlow get() = _registerStateFlow.asStateFlow()

    fun onEvent(event: RegisterEvent){
        when(event){
            is RegisterEvent.SignUp -> register(event.register)
        }
    }

    private fun register(register: Register){
        viewModelScope.launch {
            registerUseCase(register = register).collect {
                _registerStateFlow.value = it
            }
        }
    }
}