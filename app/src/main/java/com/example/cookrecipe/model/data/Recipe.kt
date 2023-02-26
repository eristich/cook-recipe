package com.example.cookrecipe.model.data

import com.example.cookrecipe.model.data.recipe.Ingredients
import com.example.cookrecipe.model.data.recipe.ProductMatches

data class Recipe(
    val id: Int,
    val title: String?,
    val image: String?,
    val imageType: String?,
    val vegetarian : Boolean?,
    val vegan: Boolean?,
    val summary: String?,
    val readyInMinutes: Int?,
    val sourceUrl: String?,
    val extendedIngredients: List<Ingredients>?,
    val productMatches: List<ProductMatches>?,
)
