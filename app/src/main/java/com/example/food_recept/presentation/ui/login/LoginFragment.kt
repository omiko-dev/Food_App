package com.example.food_recept.presentation.ui.login

import androidx.navigation.fragment.findNavController
import com.example.food_recept.BaseFragment
import com.example.food_recept.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    override fun listener() {
        goToSignUp()
    }

    private fun goToSignUp(){
        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }
    }

}