package com.example.food_recept.presentation.fragment

import androidx.navigation.fragment.findNavController
import com.example.food_recept.BaseFragment
import com.example.food_recept.databinding.FragmentSplashBinding


class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {


    override fun listener() {
        binding.btnStart.setOnClickListener {
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
        }
    }


}