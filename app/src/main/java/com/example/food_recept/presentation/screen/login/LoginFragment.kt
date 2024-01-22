package com.example.food_recept.presentation.screen.login

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.food_recept.data.common.Resource
import com.example.food_recept.databinding.FragmentLoginBinding
import com.example.food_recept.presentation.base.BaseFragment
import com.example.food_recept.presentation.model.Login
import com.example.food_recept.presentation.model.User
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
                            viewModel.insertUserInDB(
                                User(
                                    uid = it.success.uid,
                                    email = it.success.email ?: ""
                                )
                            )
                             findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToShareFragment())
                        }
                        is Resource.Error -> {}
                        is Resource.Loader -> {}
                        is Resource.Idle -> {}
                    }
                }
            }
        }
    }
}