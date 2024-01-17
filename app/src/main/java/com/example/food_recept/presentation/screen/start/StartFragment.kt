package com.example.food_recept.presentation.screen.start

import androidx.navigation.fragment.findNavController
import com.example.food_recept.presentation.base.BaseFragment
import com.example.food_recept.databinding.FragmentStartBinding


class StartFragment : BaseFragment<FragmentStartBinding>(FragmentStartBinding::inflate) {


    override fun listener() {
        binding.btnStart.setOnClickListener {
            findNavController().navigate(StartFragmentDirections.actionSplashFragmentToLoginFragment())
        }
    }


}