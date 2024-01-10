package com.example.food_recept.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.food_recept.databinding.CardFoodByCategoryBinding
import com.example.food_recept.presentation.model.FoodByCategory

class FoodByCategoryRecyclerAdapter: ListAdapter<FoodByCategory, FoodByCategoryRecyclerAdapter.FoodByCategoryCardViewHolder>(FoodByCategoryDiffUtil) {

    companion object {
        private val FoodByCategoryDiffUtil = object : DiffUtil.ItemCallback<FoodByCategory>(){
            override fun areItemsTheSame(
                oldItem: FoodByCategory,
                newItem: FoodByCategory
            ): Boolean {
                return oldItem.mealId == newItem.mealId
            }

            override fun areContentsTheSame(
                oldItem: FoodByCategory,
                newItem: FoodByCategory
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class FoodByCategoryCardViewHolder(private val binding: CardFoodByCategoryBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(){
                with(binding){
                    val food = currentList[adapterPosition]
                    tvFoodName.text = food.meal
                    Glide
                        .with(binding.root)
                        .load(food.mealImage)
                        .into(ivFoodImage)
                }
            }
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FoodByCategoryCardViewHolder {
        return FoodByCategoryCardViewHolder(
            CardFoodByCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FoodByCategoryCardViewHolder, position: Int) {
        holder.bind()
    }

}