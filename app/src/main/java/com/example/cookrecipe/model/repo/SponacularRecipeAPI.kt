package com.example.cookrecipe.model.repo

import android.util.Log
import com.example.cookrecipe.view_model.MainViewModel.Companion.TAG
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SpoonacularRecipeAPI {

    // Api Key spectacular
    private const val apiKey = "d30a2600e71348a996bbea22fc682f21"

    /**
     * Block the sending of the request to modify the header and add the api key
     */
    private val apiKeyInterceptor = Interceptor { chain ->

        val request = chain.request().newBuilder()
            .addHeader("x-api-key", " $apiKey ")
            .build()
        Log.d(TAG, "request Content: $request")
        Log.d(TAG, "request New Header: ${request.headers()} ")

        val response = chain.proceed(request)
        Log.d(TAG, "response Intercept : $response")
        response
    }


    /**
     * Configure the retrofit instance linked to the Spoonacular API.
     * The configuration is waiting until the first call
     */
    private val spoonacular: Retrofit by lazy {
        Log.d(TAG, "Request : $apiKey")

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/recipes/")
            .client(OkHttpClient.Builder().addInterceptor(apiKeyInterceptor).build())
        Log.d(TAG, "Retrofit raw data: $retrofit")

        val result = retrofit.addConverterFactory(GsonConverterFactory.create()).build()
        Log.d(TAG, "Retrofit convert data: $result")

        result
    }


    /**
     * Setup new service form spoonacular API
     * Using Interface
     */
    fun <T> createService(ServiceClass: Class<T>): T {
        return spoonacular.create(ServiceClass)
    }

}