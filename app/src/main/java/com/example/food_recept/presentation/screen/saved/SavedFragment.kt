package com.example.food_recept.presentation.screen.saved

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.food_recept.data.common.Resource
import com.example.food_recept.databinding.FragmentSavedBinding
import com.example.food_recept.presentation.base.BaseFragment
import com.example.food_recept.presentation.screen.adapter.SearchFoodCardRecyclerAdapter
import com.example.food_recept.presentation.screen.saved.event.SavedEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SavedFragment : BaseFragment<FragmentSavedBinding>(FragmentSavedBinding::inflate) {

    private val viewModel: SavedViewModel by viewModels()
    private lateinit var adapter: SearchFoodCardRecyclerAdapter

    override fun listener() {
        foodAboutListener()
    }

    override fun bind() {
        bindRecycler()
    }

    override fun observe() {
        favoriteFoodsObserve()
    }

    override fun event() {
        viewModel.onEvent(SavedEvent.GetAllFood)
    }

    private fun bindRecycler() {
        adapter = SearchFoodCardRecyclerAdapter()
        with(binding) {
            binding.recycler.adapter = adapter
            binding.recycler.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun favoriteFoodsObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.allFavoriteFoodStateFlow.collect {
                    when (it) {
                        is Resource.Success -> {
                            adapter.submitList(it.success)
                            binding.loader.visibility = View.GONE
                        }

                        is Resource.Error -> {
                            binding.loader.visibility = View.GONE
                        }

                        is Resource.Loader -> {
                            binding.loader.visibility = View.VISIBLE
                        }

                        is Resource.Idle -> {}
                    }
                }
            }
        }
    }

    private fun foodAboutListener() {
        adapter.onClick = { food ->
            findNavController().navigate(SavedFragmentDirections.actionSavedToFoodDetailFragment(food, null))
        }
    }
}