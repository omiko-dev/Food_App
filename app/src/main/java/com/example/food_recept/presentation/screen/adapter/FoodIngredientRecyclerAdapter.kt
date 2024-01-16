package com.example.food_recept.presentation.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.food_recept.databinding.CardFoodIngredientBinding
import com.example.food_recept.presentation.extension.glide
import com.example.food_recept.presentation.model.Food

class FoodIngredientRecyclerAdapter: RecyclerView.Adapter<FoodIngredientRecyclerAdapter.AboutFoodViewHolder>() {

    private val imageURL = "https://www.themealdb.com/images/ingredients/"

    inner class AboutFoodViewHolder(private val binding: CardFoodIngredientBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(){
                val ingredient = food.ingredients[layoutPosition]
                val measure = food.measures[layoutPosition]
                with(binding){
                    tvIngredient.text = ingredient
                    tvMeasure.text = measure
                    root.glide(ivIngredientImg, "${imageURL + ingredient}.png")
                }



            }
        }

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
        youtube = "ss"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AboutFoodViewHolder {
        return AboutFoodViewHolder(
            CardFoodIngredientBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return food.ingredients.size
    }

    override fun onBindViewHolder(holder: AboutFoodViewHolder, position: Int) {
        holder.bind()
    }

    fun setFood(newFood: Food){
        food = newFood
        notifyDataSetChanged()
    }
}