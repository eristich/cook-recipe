package com.example.cookrecipe.view

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cookrecipe.R
import com.example.cookrecipe.view_model.RecipeViewModel

class RecipeActivity : AppCompatActivity() {

    private lateinit var viewModel: RecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        viewModel = ViewModelProvider(this@RecipeActivity)[RecipeViewModel::class.java]

        // get the recipe value
        viewModel.getRecipe()
    }

    override fun onResume() {
        super.onResume()

        /*TODO Insert data Recipe here */

        /**
         * Demo sharing data
         */
        val sharingBtn = findViewById<Button>(R.id.btn_sharing)
        sharingBtn.setOnClickListener {
            //viewModel.getRecipe()
            viewModel.shareRecipe(this@RecipeActivity)

        }


    }
}