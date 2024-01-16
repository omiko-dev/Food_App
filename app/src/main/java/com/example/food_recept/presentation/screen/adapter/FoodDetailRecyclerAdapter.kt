package com.example.food_recept.presentation.screen.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.food_recept.R
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

    lateinit var goBackOnClick: () -> Unit

    lateinit var sourceOnClick: () -> Unit

    private lateinit var adapter: FoodIngredientRecyclerAdapter

    inner class FoodDetailViewHolder(private val binding: FoodDetailViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            with(binding) {
                tvCategory.text = food.category
                tvCountry.text = food.country
                tvName.text = food.meal
                tvInstruct.text = food.instructions
                root.glide(ivFoodImage, food.mealImage)

                adapter = FoodIngredientRecyclerAdapter()
                recycler.adapter = adapter
                recycler.layoutManager = LinearLayoutManager(root.context)
                adapter.setFood(food)
            }
        }

        fun listener(){
            with(binding){
                radioGroup.setOnCheckedChangeListener { _, checkedId ->
                    when(checkedId){
                        rbIngredient.id -> {
                            rbIngredient.background = ContextCompat.getDrawable(binding.root.context, R.drawable.shape_green_bg)
                            rbIngredient.setTextColor(Color.WHITE)
                            rbInstruct.background = ContextCompat.getDrawable(binding.root.context, R.drawable.shape_green_border)
                            rbInstruct.setTextColor(ContextCompat.getColor(root.context, R.color.green))

                            recycler.visibility = View.VISIBLE
                            tvInstruct.visibility = View.GONE
                        }
                        rbInstruct.id -> {
                            rbIngredient.background = ContextCompat.getDrawable(binding.root.context, R.drawable.shape_green_border)
                            rbIngredient.setTextColor(ContextCompat.getColor(root.context, R.color.green))
                            rbInstruct.background = ContextCompat.getDrawable(binding.root.context, R.drawable.shape_green_bg)
                            rbInstruct.setTextColor(Color.WHITE)

                            tvInstruct.visibility = View.VISIBLE
                            recycler.visibility = View.GONE
                        }
                    }
                }
            }
        }

        fun goBackOnClickListener(){
            binding.ivGoBack.setOnClickListener {
                goBackOnClick()
            }
        }

        fun sourceOnClickListener(){
            binding.ivSource.setOnClickListener {
                sourceOnClick()
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
            listener()
            goBackOnClickListener()
            sourceOnClickListener()
        }
    }

    fun setFood(newFood: Food) {
        food = newFood
        notifyDataSetChanged()
    }
}