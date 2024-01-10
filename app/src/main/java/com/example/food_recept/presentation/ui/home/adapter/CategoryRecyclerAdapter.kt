package com.example.food_recept.presentation.ui.home.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.food_recept.R
import com.example.food_recept.databinding.CategoryButtonBinding
import com.example.food_recept.presentation.model.Category

class CategoryRecyclerAdapter :
    RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryButtonViewHolder>() {

    private var isCheck = false
    private var lastCheckedRadioButtonIndex = 0
    private var categoryList: List<Category> = listOf()
    lateinit var categoryOnClick: (String) -> Unit

    inner class CategoryButtonViewHolder(private val binding: CategoryButtonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val category = categoryList[adapterPosition]
            with(binding) {
                btnCategory.text = category.category
                if (isCheck) {
                    btnCategory.isChecked = category.isChecked
                    if (!category.isChecked) {
                        btnCategory.setTextColor(
                            ContextCompat.getColor(
                                root.context,
                                R.color.green
                            )
                        )
                        btnCategory.setBackgroundColor(Color.TRANSPARENT)
                    }
                } else {
                    btnCategory.setTextColor(ContextCompat.getColor(root.context, R.color.green))
                    btnCategory.setBackgroundColor(Color.TRANSPARENT)
                    if (adapterPosition == 0) {
                        btnCategory.setTextColor(Color.WHITE)
                        btnCategory.background =
                            ContextCompat.getDrawable(root.context, R.drawable.shape_green_bg)
                        btnCategory.isChecked = true
                        lastCheckedRadioButtonIndex = 0
                    }
                }
            }
        }

        fun categoryListener() {
            with(binding) {
                btnCategory.setOnClickListener {
                    if(lastCheckedRadioButtonIndex != adapterPosition){
                        val category = categoryList[adapterPosition]
                        handleRadioButtonChecks(adapterPosition)
                        btnCategory.setTextColor(Color.WHITE)
                        btnCategory.background =
                            ContextCompat.getDrawable(root.context, R.drawable.shape_green_bg)
                        categoryOnClick(category.category)
                    }
                }
            }
        }
    }

    private fun handleRadioButtonChecks(adapterPosition: Int) {
        isCheck = true
        categoryList[lastCheckedRadioButtonIndex].isChecked = false
        categoryList[adapterPosition].isChecked = true
        notifyItemChanged(lastCheckedRadioButtonIndex)
        lastCheckedRadioButtonIndex = adapterPosition
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryButtonViewHolder {
        return CategoryButtonViewHolder(
            CategoryButtonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryButtonViewHolder, position: Int) {
        with(holder) {
            bind()
            categoryListener()
        }
    }

    fun setData(newCategory: List<Category>) {
        categoryList = newCategory
        notifyDataSetChanged()
    }
}