package com.example.food_recept.presentation.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.food_recept.databinding.FoodDetailViewBinding
import com.example.food_recept.presentation.extension.glide
import com.example.food_recept.presentation.model.Food

class FoodDetailRecyclerAdapter :
    RecyclerView.Adapter<FoodDetailRecyclerAdapter.FoodDetailViewHolder>() {


    private var food: Food = Food(
        idMeal = "",
        country = "",
        category = "",
        ingredients = listOf(),
        instructions = "",
        meal = "",
        mealImage = "",
        measures = listOf(),
        source = null,
        youtube = ""
    )

    var goBackOnClick: (() -> Unit)? = null
    var sourceOnClick: (() -> Unit)? = null
    lateinit var setFavoriteOnClick: ((Food) -> Unit)

    inner class FoodDetailViewHolder(private val binding: FoodDetailViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            with(binding) {
                tvCategory.text = food.category
                tvCountry.text = food.country
                tvName.text = food.meal
                tvInstruct.text = food.instructions
                root.glide(ivFoodImage, food.mealImage)
            }
        }

        fun goBackOnClick() {
            binding.ivGoBack.setOnClickListener {
                goBackOnClick?.invoke()
            }
        }

        fun sourceOnClick() {
            binding.ivSource.setOnClickListener {
                sourceOnClick?.invoke()
            }
        }

        fun setFavoriteOnClick() {
            binding.ibAddFavorite.setOnClickListener {
                setFavoriteOnClick.invoke(food)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodDetailViewHolder {
        return FoodDetailViewHolder(
            FoodDetailViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: FoodDetailViewHolder, position: Int) {
        with(holder){
            bind()
            goBackOnClick()
            sourceOnClick()
            setFavoriteOnClick()
        }
    }

    fun setFood(newFood: Food) {
        food = newFood
        notifyDataSetChanged()
    }
}