package com.example.food_recept.presentation.ui.search

import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.food_recept.BaseFragment
import com.example.food_recept.data.common.Resource
import com.example.food_recept.databinding.FragmentSearchBinding
import com.example.food_recept.presentation.ui.search.adapter.SearchFoodCardRecyclerAdapter
import com.example.food_recept.presentation.ui.search.event.SearchEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var adapter: SearchFoodCardRecyclerAdapter
    private val args: SearchFragmentArgs by navArgs()

    override fun listener() {
        searchFoodListener()
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
                            binding.tvErrorMessage.visibility = View.VISIBLE
                        }
                        is Resource.Error -> {
                        }
                        is Resource.Loader -> binding.loader.visibility = View.VISIBLE
                        is Resource.Idle -> binding.loader.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun bindAdapter(){
        adapter = SearchFoodCardRecyclerAdapter()
        with(binding){
            recycler.adapter = adapter
            recycler.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun searchFoodListener(){
        with(binding){
            ibSearchClick.setOnClickListener {
                val name = etSearch.text.toString()
                name.let {
                    viewModel.onEvent(SearchEvent.GetFoodByName(name))
                }
            }
        }
    }

    private fun searchFoodByArgs(){
        args.search?.let {
            binding.etSearch.setText(it, TextView.BufferType.NORMAL)
            viewModel.onEvent(SearchEvent.GetFoodByName(it))
        }
    }
}

