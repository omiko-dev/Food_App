package com.example.food_recept.presentation.screen.search

import android.view.View
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.food_recept.BaseFragment
import com.example.food_recept.data.common.Resource
import com.example.food_recept.databinding.FragmentSearchBinding
import com.example.food_recept.presentation.model.Category
import com.example.food_recept.presentation.model.Country
import com.example.food_recept.presentation.screen.adapter.SearchFoodCardRecyclerAdapter
import com.example.food_recept.presentation.screen.dialog.FoodFilterSheet
import com.example.food_recept.presentation.screen.search.event.SearchEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var adapter: SearchFoodCardRecyclerAdapter
    private val args: SearchFragmentArgs by navArgs()
    private val foodFilterSheet = FoodFilterSheet()
    private var countryList: List<Country> = listOf()
    private var categoryList: List<Category> = listOf()

    override fun listener() {
        searchFoodListener()
        filterListener()
        searchFieldListener()
        goToHomeListener()
        goToFoodAboutListener()
    }

    override fun bind() {
        bindAdapter()
        searchFoodByArgs()
    }

    override fun event() {
        viewModel.onEvent(SearchEvent.GetCategoryList)
        viewModel.onEvent(SearchEvent.GetCountryList)
    }

    override fun observe() {
        foodByNameObserve()
        categoryObserve()
        countryObserve()
    }

    private fun foodByNameObserve(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.foodByNameStateFlow.collect {
                    when(it) {
                        is Resource.Success -> {
                            val test = it.success
                            foodFilterSheet.onClick = {
                                adapter.submitList(
                                    test.filter { item -> it.country == item.country && it.category == item.category }
                                )
                            }
                            adapter.submitList(it.success)
                        }
                        is Resource.Error -> {}
                        is Resource.Loader -> binding.loader.visibility = View.VISIBLE
                        is Resource.Idle -> binding.loader.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun categoryObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.categoryStateFlow.collect {
                    when (it) {
                        is Resource.Success -> {
                            categoryList = it.success
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

    private fun countryObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.countryStateFlow.collect {
                    when (it) {
                        is Resource.Success -> {

                            countryList = it.success
                        }

                        is Resource.Error -> {

                        }

                        is Resource.Loader -> {

                        }

                        is Resource.Idle -> {

                        }
                    }
                }
            }
        }
    }

    private fun bindAdapter() {
        adapter = SearchFoodCardRecyclerAdapter()
        with(binding) {
            recycler.adapter = adapter
            recycler.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun searchFoodListener() {
        with(binding) {
            ibSearchClick.setOnClickListener {
                foodFilterSheet.show(parentFragmentManager, tag)
                foodFilterSheet.categoryList = categoryList
                foodFilterSheet.countryList = countryList
            }
        }
    }

    private fun searchFieldListener(){
        binding.etSearch.addTextChangedListener {
            viewModel.onEvent(SearchEvent.GetFoodByName(it.toString()))
        }
    }

    private fun searchFoodByArgs() {
        args.search?.let {
            binding.etSearch.setText(it, TextView.BufferType.NORMAL)
            viewModel.onEvent(SearchEvent.GetFoodByName(it))
        }
    }

    private fun filterListener() {
//        foodFilterSheet.onClick = {
////            viewModel.getFilteredFood(it.category, it.country)
//        }
    }

    private fun goToHomeListener(){
        binding.ivGoBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun goToFoodAboutListener(){
        adapter.onClick = {
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToFoodDetailFragment(it))
        }
    }
}

