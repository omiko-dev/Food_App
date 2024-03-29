package com.example.food_recept.presentation.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.food_recept.databinding.SearchFoodCardBinding
import com.example.food_recept.presentation.model.Food

class SearchFoodCardRecyclerAdapter :
    ListAdapter<Food, SearchFoodCardRecyclerAdapter.SearchFoodCardViewHolder>(
        FoodDiffUtil
    ) {

    companion object {
        private val FoodDiffUtil = object : DiffUtil.ItemCallback<Food>() {
            override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
                return oldItem.idMeal == newItem.idMeal
            }

            override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
                return oldItem == newItem
            }
        }
    }

    lateinit var onClick: (Food) -> Unit

    inner class SearchFoodCardViewHolder(private val binding: SearchFoodCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            with(binding) {
                val meal = currentList[layoutPosition]
                tvName.text = meal.meal
                tvCategory.text = meal.category
                Glide
                    .with(root)
                    .load(meal.mealImage)
                    .into(ivImage)
            }
        }

        fun listener(){
            binding.root.setOnClickListener {
                onClick(currentList[layoutPosition])
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchFoodCardViewHolder {
        return SearchFoodCardViewHolder(
            SearchFoodCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchFoodCardViewHolder, position: Int) {
        with(holder){
            bind()
            listener()
        }
    }
}