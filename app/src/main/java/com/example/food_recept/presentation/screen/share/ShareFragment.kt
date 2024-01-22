package com.example.food_recept.presentation.screen.share

import androidx.navigation.findNavController
import com.example.food_recept.R
import com.example.food_recept.databinding.FragmentShareBinding
import com.example.food_recept.presentation.base.BaseFragment
import com.example.food_recept.presentation.screen.home.HomeFragmentDirections
import com.example.food_recept.presentation.screen.saved.SavedFragmentDirections

class ShareFragment : BaseFragment<FragmentShareBinding>(FragmentShareBinding::inflate) {
    override fun listener() {
        with(binding) {
            bottomNavigationView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_home -> {
                        fragmentContainerView2.findNavController().navigate(HomeFragmentDirections.actionGlobalHome())
                        return@setOnItemSelectedListener true
                    }

                    R.id.menu_saved -> {
                        fragmentContainerView2.findNavController().navigate(SavedFragmentDirections.actionGlobalSaved())
                        return@setOnItemSelectedListener true
                    }
                }
                return@setOnItemSelectedListener true
            }
        }
    }
}

