package com.example.food_recept.presentation.screen.splash

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.food_recept.data.common.Resource
import com.example.food_recept.databinding.FragmentSplashBinding
import com.example.food_recept.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    private val viewModel: SplashViewModel by viewModels()

    override fun listener() {

    }

    override fun observe() {
        authorizeUserObserve()
        observeNavigationEvents()
    }

    private fun authorizeUserObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.authorizeUserStateFlow.collect {
                    when (it) {
                        is Resource.Success -> {
                            if (it.success == null) {
                                viewModel.navigateToLogInFragment()
                            } else {
                                viewModel.navigateToHomeFragment()
                            }
                        }

                        is Resource.Error -> {
                            viewModel.navigateToLogInFragment()
                        }

                        is Resource.Loader -> {
                            binding.loader.visibility = View.VISIBLE
                        }

                        is Resource.Idle -> {
                            binding.loader.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }


    private fun observeNavigationEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.splashNavigationStateFlow.collect { navigationEvent ->
                    when (navigationEvent) {
                        is SplashNavigation.NavigateShareFragment -> {
                            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToShareFragment())
                        }

                        is SplashNavigation.NavigateLogInFragment -> {
                            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
                        }

                        else -> {}
                    }
                }
            }
        }
    }

}