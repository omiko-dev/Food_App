package com.example.food_recept.presentation.extension

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.glide(view: ImageView, load: String) {
    Glide.with(this).load(load).into(view)
}