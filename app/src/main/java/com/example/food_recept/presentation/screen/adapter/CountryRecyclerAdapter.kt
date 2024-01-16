package com.example.food_recept.presentation.screen.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.food_recept.R
import com.example.food_recept.databinding.CountryButtonBinding
import com.example.food_recept.presentation.model.Country

class CountryRecyclerAdapter :
    RecyclerView.Adapter<CountryRecyclerAdapter.CountryButtonViewHolder>() {

    private var isCheck = false
    private var lastCheckedRadioButtonIndex = 0
    private var categoryList: List<Country> = listOf()
    lateinit var categoryOnClick: (String) -> Unit

    inner class CountryButtonViewHolder(private val binding: CountryButtonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val country = categoryList[layoutPosition]
            with(binding) {
                btnCountry.text = country.country
                if (isCheck) {
                    btnCountry.isChecked = country.isChecked
                    if (!country.isChecked) {
                        btnCountry.setTextColor(
                            ContextCompat.getColor(
                                root.context,
                                R.color.green
                            )
                        )
                        btnCountry.setBackgroundColor(Color.TRANSPARENT)
                    }
                } else {
                    btnCountry.setTextColor(ContextCompat.getColor(root.context, R.color.green))
                    btnCountry.setBackgroundColor(Color.TRANSPARENT)
                    if (layoutPosition == 0) {
                        btnCountry.setTextColor(Color.WHITE)
                        btnCountry.background =
                            ContextCompat.getDrawable(root.context, R.drawable.shape_green_bg)
                        btnCountry.isChecked = true
                        lastCheckedRadioButtonIndex = 0
                    }
                }
            }
        }

        fun categoryListener() {
            with(binding) {
                btnCountry.setOnClickListener {
                    if(lastCheckedRadioButtonIndex != layoutPosition){
                        val country = categoryList[layoutPosition]
                        handleRadioButtonChecks(layoutPosition)
                        btnCountry.setTextColor(Color.WHITE)
                        btnCountry.background =
                            ContextCompat.getDrawable(root.context, R.drawable.shape_green_bg)
                        categoryOnClick(country.country)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryButtonViewHolder {
        return CountryButtonViewHolder(
            CountryButtonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CountryButtonViewHolder, position: Int) {
        with(holder) {
            bind()
            categoryListener()
        }
    }

    fun setData(country: List<Country>) {
        categoryList = country
        notifyDataSetChanged()
    }
}