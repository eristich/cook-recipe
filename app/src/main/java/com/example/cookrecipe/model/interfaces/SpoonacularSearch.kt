package com.example.cookrecipe.model.interfaces

import com.example.cookrecipe.model.data.RecipeSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SpoonacularSearch {
    @GET("complexSearch")

    suspend fun searchRecipe(
        @Query("query") searchVal: String,
        @Query("number") number: Int?,
    ): Response<RecipeSearchResponse>
}