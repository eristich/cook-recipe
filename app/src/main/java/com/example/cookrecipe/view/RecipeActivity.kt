package com.example.cookrecipe.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cookrecipe.R
import com.example.cookrecipe.view_model.RecipeViewModel

class RecipeActivity : AppCompatActivity() {

    private lateinit var viewModel: RecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
        /*TODO Insert Call Recipe Api here*/
    }

    override fun onResume() {
        super.onResume()

        /*TODO Insert data Recipe here */

    }
}