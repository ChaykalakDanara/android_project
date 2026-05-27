package com.chaykalak.mycookbook.data

import androidx.annotation.DrawableRes

data class Recipe(
    val id: Int,
    val name: String,
    val description: String,
    val ingredients: List<String>,
    val steps: List<String>,
    val prepTime: String,
    val difficulty: String,
    @DrawableRes val imageResId: Int
)