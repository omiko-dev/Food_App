package com.example.food_recept.presentation.ui.share

import androidx.navigation.findNavController
import com.example.food_recept.BaseFragment
import com.example.food_recept.R
import com.example.food_recept.databinding.FragmentShareBinding
import com.example.food_recept.presentation.ui.home.HomeFragmentDirections
import com.example.food_recept.presentation.ui.notification.NotificationFragmentDirections
import com.example.food_recept.presentation.ui.profile.ProfileFragmentDirections
import com.example.food_recept.presentation.ui.saved.SavedFragmentDirections

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

                    R.id.menu_notification -> {
                        fragmentContainerView2.findNavController().navigate(NotificationFragmentDirections.actionGlobalNotification())
                        return@setOnItemSelectedListener true
                    }

                    R.id.menu_profile -> {
                        fragmentContainerView2.findNavController().navigate(ProfileFragmentDirections.actionGlobalProfile())
                        return@setOnItemSelectedListener true
                    }
                }
                return@setOnItemSelectedListener true
            }
        }
    }
}

