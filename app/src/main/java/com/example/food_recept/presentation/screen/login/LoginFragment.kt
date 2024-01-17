package com.example.food_recept.presentation.screen.login

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.food_recept.presentation.base.BaseFragment
import com.example.food_recept.data.remote.common.Resource
import com.example.food_recept.databinding.FragmentLoginBinding
import com.example.food_recept.presentation.model.Login
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    private val viewModel: LoginViewModel by viewModels()

    override fun listener() {
        goToSignUp()
        loginListener()
    }

    override fun observe() {
        loginObserve()
    }

    private fun goToSignUp(){
        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }
    }

    private fun loginListener(){
        with(binding) {
            binding.
            btnLogin.setOnClickListener {
                viewModel.login(Login(email = etEmail.text.toString(), password = etPassword.text.toString()))
            }
        }
    }

    private fun loginObserve(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.loginStateFlow.collect {
                    when(it){
                        is Resource.Success -> {
                            Log.i("omiko", it.success.token)
                        }
                        is Resource.Error -> {
                            Log.i("omiko", it.error)
                        }
                        is Resource.Loader -> {}
                        is Resource.Idle -> {}
                    }
                }
            }
        }
    }
}