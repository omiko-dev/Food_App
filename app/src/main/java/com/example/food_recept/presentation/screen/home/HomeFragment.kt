package com.example.food_recept.presentation.screen.home

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.food_recept.data.common.Resource
import com.example.food_recept.databinding.FragmentHomeBinding
import com.example.food_recept.presentation.base.BaseFragment
import com.example.food_recept.presentation.screen.adapter.CategoryRecyclerAdapter
import com.example.food_recept.presentation.screen.adapter.FoodByCategoryRecyclerAdapter
import com.example.food_recept.presentation.screen.home.event.HomeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var filterAdapter: CategoryRecyclerAdapter
    private lateinit var foodByCategoryAdapter: FoodByCategoryRecyclerAdapter

    override fun listener() {
        getFoodByCategoryListener()
        goToSearchListener()
        logOutListener()
    }

    override fun bind() {
        bindFoodByCategoryRecycler()
        bindCategoryRecycler()
    }

    override fun observe() {
        foodByCategoryObserve()
        singleRandomFoodObserve()
        categoryObserve()
    }

    override fun event() {
        viewModel.onEvent(HomeEvent.GetCategory)
        viewModel.onEvent(HomeEvent.GetSingleRandomFood)
    }

    private fun categoryObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.categoryStateFlow.collect {
                    when (it) {
                        is Resource.Success -> {
                            filterAdapter.setData(it.success)
                            viewModel.onEvent(HomeEvent.GetFoodByCategory(it.success[0].category))
                        }

                        is Resource.Error -> {}
                        is Resource.Loader -> {}
                        is Resource.Idle -> {}
                    }
                }
            }
        }
    }

    private fun foodByCategoryObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.foodByCategoryStateFlow.collect {
                    when (it) {
                        is Resource.Success -> {
                            foodByCategoryAdapter.submitList(it.success)
                        }

                        is Resource.Error -> {}
                        is Resource.Loader -> {}
                        is Resource.Idle -> {}
                    }
                }
            }
        }
    }

    private fun getFoodByCategoryListener() {
        filterAdapter.categoryOnClick = {
            viewModel.onEvent(HomeEvent.GetFoodByCategory(it))
        }
    }

    private fun bindCategoryRecycler() {
        filterAdapter = CategoryRecyclerAdapter()
        with(binding) {
            recyclerFilter.adapter = filterAdapter
            recyclerFilter.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun bindFoodByCategoryRecycler() {
        foodByCategoryAdapter = FoodByCategoryRecyclerAdapter()
        with(binding) {
            recyclerFoodByCategoryCard.adapter = foodByCategoryAdapter
            recyclerFoodByCategoryCard.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun singleRandomFoodObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.singleRandomStateFlow.collect {
                    when (it) {
                        is Resource.Success -> {
                            with(binding) {

                                tvCategory.text = it.success.category
                                tvCountry.text = it.success.country
                                tvFoodName.text = it.success.meal
                                Glide
                                    .with(requireContext())
                                    .load(it.success.mealImage)
                                    .into(ivDailyFoodImg)
                                tvCountryTitle.visibility = View.VISIBLE
                                tvCategoryTitle.visibility = View.VISIBLE
                                vDailyMealView.visibility = View.VISIBLE
                                goToFoodDetailListener(it.success.idMeal)
                            }
                        }

                        is Resource.Loader -> {
                            binding.dailyLoader.visibility = View.VISIBLE
                        }
                        is Resource.Idle -> {
                            binding.dailyLoader.visibility = View.GONE
                        }
                        is Resource.Error -> {}
                    }
                }
            }
        }
    }

    private fun goToSearchListener(){
        with(binding){
            ibSearch.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeToSearchFragment(
                    etSearch.text.toString()
                ))
            }
        }
    }

    private fun goToFoodDetailListener(id: String){
        foodByCategoryAdapter.onClick = {
            findNavController().navigate(HomeFragmentDirections.actionHomeToFoodDetailFragment(null, it))
        }

        binding.vDailyMealView.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeToFoodDetailFragment(null, id))
        }
    }

    private fun logOutListener(){
        binding.ivLogOut.setOnClickListener {
            viewModel.onEvent(HomeEvent.LogOut)
            findNavController().popBackStack()
        }
    }
}