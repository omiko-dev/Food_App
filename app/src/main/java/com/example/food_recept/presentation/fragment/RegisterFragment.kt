package com.example.food_recept.presentation.fragment

import androidx.navigation.fragment.findNavController
import com.example.food_recept.BaseFragment
import com.example.food_recept.databinding.FragmentRegisterBinding


class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {
    override fun listener() {
        goToLogin()
    }

    private fun goToLogin(){
        binding.tvSignUp.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}