package com.example.cookrecipe.view

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProvider
import com.example.cookrecipe.R
import com.example.cookrecipe.view_model.RecipeViewModel
import com.squareup.picasso.Picasso

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
        // create pointer of view
        val recipeTitle: TextView = findViewById(R.id.recipe_title)
        val recipeDesc: TextView = findViewById(R.id.recipe_desc)
        val recipeUrl: TextView = findViewById(R.id.recipe_url)
        val recipeReadyInMinutes: TextView = findViewById(R.id.recipe_readyInMinutes)
        val recipeIngredients: TextView = findViewById(R.id.recipe_ingredient)
        // first image
        val recipeMasterPicture: ImageView = findViewById(R.id.recipe_master_picture)
        // Icon
        val recipeVegan : ImageView = findViewById(R.id.recipe_vegan_icon)
        val recipeVegetarian : ImageView = findViewById(R.id.recipe_vegetarian_icon)
        /**
         * recipe data display
         */
        viewModel.recipe.observe(this@RecipeActivity) { recipe ->
            /**
             * Implement attribute of model view
             */
            viewModel.title = recipe.title.toString()
            viewModel.url = recipe.sourceUrl.toString()
            /**
             * Setup text and title
             */
            recipeTitle.text = viewModel.title
            recipeReadyInMinutes.text =
                getString(R.string.readyInMinutes, recipe.readyInMinutes.toString())
            recipeUrl.text = getString(R.string.recipe_url, viewModel.url)

            // List of ingredients

            val ingredients = recipe.extendedIngredients

            if (ingredients != null) {
                var comma = ", "
                val max = ingredients.size
                for ((count, ingredient) in ingredients.withIndex()) {
                    recipeIngredients.append(ingredient.name + comma)
                    if (count == (max - 1)) {
                        comma = ""
                    }
                }
            }


            /**
             * Convert html to text part
             * */
            recipeDesc.text =
                HtmlCompat.fromHtml(recipe.summary.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)

            /**
             * Picture parte
             */
            Picasso.get().load(recipe.image.toString()).into(recipeMasterPicture)

            /**
             * Icon Manager
             */
            if (recipe.vegan == true){
                recipeVegan.setImageResource(R.drawable.vegan_logo)
            }

            if (recipe.vegetarian == true){
                recipeVegetarian.setImageResource(R.drawable.vegtariane_logo)
            }


        }

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