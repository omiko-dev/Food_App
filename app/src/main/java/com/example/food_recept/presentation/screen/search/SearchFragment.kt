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
import com.example.food_recept.data.common.Resource
import com.example.food_recept.databinding.FragmentSearchBinding
import com.example.food_recept.presentation.base.BaseFragment
import com.example.food_recept.presentation.screen.adapter.SearchFoodCardRecyclerAdapter
import com.example.food_recept.presentation.screen.search.event.SearchEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var adapter: SearchFoodCardRecyclerAdapter
    private val args: SearchFragmentArgs by navArgs()

    override fun listener() {
        searchFieldListener()
        goToHomeListener()
        goToFoodAboutListener()
    }

    override fun bind() {
        bindAdapter()
        searchFoodByArgs()
    }

    override fun observe() {
        foodByNameObserve()
    }

    private fun foodByNameObserve(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.foodByNameStateFlow.collect {
                    when(it) {
                        is Resource.Success -> {
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

    private fun bindAdapter() {
        adapter = SearchFoodCardRecyclerAdapter()
        with(binding) {
            recycler.adapter = adapter
            recycler.layoutManager = GridLayoutManager(requireContext(), 2)
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

    private fun goToHomeListener(){
        binding.ivGoBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun goToFoodAboutListener(){
        adapter.onClick = {
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToFoodDetailFragment(
                    it,
                    null
                )
            )
        }
    }
}

