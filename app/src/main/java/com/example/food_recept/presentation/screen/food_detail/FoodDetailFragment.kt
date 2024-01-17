package com.example.food_recept.presentation.screen.food_detail

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.food_recept.presentation.screen.dialog.ActionDialogFragment
import com.example.food_recept.presentation.base.BaseFragment
import com.example.food_recept.data.remote.common.Resource
import com.example.food_recept.databinding.FragmentFoodDetailBinding
import com.example.food_recept.presentation.screen.adapter.FoodDetailRecyclerAdapter
import com.example.food_recept.presentation.screen.food_detail.event.FoodDetailEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class FoodDetailFragment : BaseFragment<FragmentFoodDetailBinding>(FragmentFoodDetailBinding::inflate) {

    private val args: FoodDetailFragmentArgs by navArgs()
    private val viewModel: FoodDetailViewModel by viewModels()
    private lateinit var adapter: FoodDetailRecyclerAdapter
    private val dialog = ActionDialogFragment()

    override fun listener() {
        goToHomeListener()
        showSourceDialogListener()
    }

    override fun observe() {
        foodByDetailObserve()
    }

    override fun event() {
        getFoodByDetailEvent()
    }

    override fun bind() {
        bindIngredientRecycler()
    }

    private fun getFoodByDetailEvent(){
        viewModel.onEvent(FoodDetailEvent.GetFoodById(args.id))
    }


    private fun foodByDetailObserve(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.foodDetailStateFlow.collect {
                    when(it){
                        is Resource.Success -> {
                            it.success.ingredients = it.success.ingredients.filter { it != null && it.isNotBlank() }
                            it.success.measures = it.success.measures.filter { it != null && it.isNotBlank() }
                            adapter.setFood(it.success)
                        }
                        is Resource.Error -> {}
                        is Resource.Loader -> {}
                        is Resource.Idle -> {}
                    }
                }
            }
        }
    }

    private fun bindIngredientRecycler(){
        adapter = FoodDetailRecyclerAdapter()
        with(binding){
            recycler.adapter = adapter
            recycler.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun goToHomeListener(){
        adapter.goBackOnClick = {
            findNavController().popBackStack()
        }
    }

    private fun showSourceDialogListener(){
        adapter.sourceOnClick = {
            dialog.show(parentFragmentManager, tag)
        }
    }

}