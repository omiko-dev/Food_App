package com.example.food_recept.presentation.screen.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.food_recept.databinding.FragmentFoodFilterSheetBinding
import com.example.food_recept.presentation.model.Category
import com.example.food_recept.presentation.model.Country
import com.example.food_recept.presentation.model.CountryAndCategoryFilterModel
import com.example.food_recept.presentation.screen.adapter.CategoryRecyclerAdapter
import com.example.food_recept.presentation.screen.adapter.CountryRecyclerAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class FoodFilterSheet : BottomSheetDialogFragment() {

    private var _binding: FragmentFoodFilterSheetBinding? = null
    val binding get() = _binding!!
    private lateinit var categoryAdapter: CategoryRecyclerAdapter
    private lateinit var countryAdapter: CountryRecyclerAdapter
    var categoryList: List<Category> = listOf()
    var countryList: List<Country> = listOf()
    private var filterData: CountryAndCategoryFilterModel = CountryAndCategoryFilterModel()
    lateinit var onClick: (CountryAndCategoryFilterModel) -> Unit

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFoodFilterSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        listener()
    }

    private fun bind(){
        bindCategoryAdapter()
        bindCountryAdapter()
    }
    private fun listener(){
        filterButtonListener()
        filterFoodListener()
    }

    private fun bindCategoryAdapter(){
        categoryAdapter = CategoryRecyclerAdapter()
        with(binding){
            categoryRecycler.adapter = categoryAdapter
            categoryRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        categoryAdapter.setData(categoryList)
    }

    private fun bindCountryAdapter(){
        countryAdapter = CountryRecyclerAdapter()
        with(binding){
            countryRecycler.adapter = countryAdapter
            countryRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        countryAdapter.setData(countryList)
    }

    private fun filterButtonListener(){
        countryAdapter.categoryOnClick = {
            filterData.country = it
        }
        categoryAdapter.categoryOnClick = {
            filterData.category = it
        }
    }

    private fun filterFoodListener(){
        binding.btnFilter.setOnClickListener {
            onClick(filterData)
            this.dismiss()
        }
    }
}