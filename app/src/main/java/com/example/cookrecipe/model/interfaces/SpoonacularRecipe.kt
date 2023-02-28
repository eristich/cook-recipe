package com.example.cookrecipe.model.interfaces

import com.example.cookrecipe.model.data.Recipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SpoonacularRecipe {

    @GET("{recipeId}/information")

    suspend fun getThisRecipe(
        @Path("recipeId") recipeId: Int,
    ):Response<Recipe>

}