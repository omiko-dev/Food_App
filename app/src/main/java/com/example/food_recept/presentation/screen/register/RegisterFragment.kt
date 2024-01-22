package com.example.food_recept.presentation.screen.register

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.food_recept.data.common.Resource
import com.example.food_recept.databinding.FragmentRegisterBinding
import com.example.food_recept.presentation.base.BaseFragment
import com.example.food_recept.presentation.model.Register
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {
    private val viewModel: RegisterViewModel by viewModels()

    override fun listener() {
        goToLogin()
        registerListener()
    }

    override fun observe() {
        registerObserve()
    }

    private fun goToLogin(){
        binding.tvSignUp.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun registerObserve(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.registerStateFlow.collect {
                    when(it){
                        is Resource.Success -> {
                            findNavController().popBackStack()
                        }
                        is Resource.Error -> {

                        }
                        is Resource.Loader -> {}
                        is Resource.Idle -> {}
                    }
                }
            }
        }
    }

    private fun registerListener(){
        binding.btnLogin.setOnClickListener {
            viewModel.test(
                Register(
                    name = binding.etName.text.toString(),
                    email = binding.etEmail.text.toString(),
                    password = binding.etPassword.text.toString()
                )
            )
        }
    }

}