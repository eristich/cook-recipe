package com.example.cookrecipe.model.interfaces

import com.example.cookrecipe.model.data.Recipe
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SpoonacularRecipe {

    @GET("{recipeId}/information")

    fun getThisRecipe(
        @Path("recipeId") recipeId: Int,
    ):Call<Recipe>

}