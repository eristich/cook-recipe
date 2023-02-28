package com.example.cookrecipe.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cookrecipe.R
import com.example.cookrecipe.databinding.ActivityMainBinding
import com.example.cookrecipe.view.adapters.OnItemClickListener
import com.example.cookrecipe.view.adapters.RecipeSearchListAdapter
import com.example.cookrecipe.view_model.MainViewModel
import com.example.cookrecipe.view_model.MainViewModel.Companion.TAG

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recipeSearchList: RecyclerView // = findViewById<RecyclerView>(R.id.recipe_search_list_recycler_view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set Main view model
        viewModel = ViewModelProvider(this@MainActivity)[MainViewModel::class.java]

        // Set binding modelView bidirectional com
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this@MainActivity,R.layout.activity_main)
        // Set binding
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@MainActivity
        Log.d(TAG, "onCreate: ${viewModel.searchVal}")

        // configure recycler view
        recipeSearchList = findViewById(R.id.recipe_search_list_recycler_view)
        recipeSearchList.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()

        //set view text
        val homeTextLog: TextView = findViewById(R.id.homeText2)

        //Set listener of btn
        val btnLogin: Button = findViewById(R.id.btn_login)

        val user = viewModel.getUser()

        /**
         * User UI Part
         */

        // If user is logged
        if (user != null) {
            Log.d(TAG, "onCreate: ${user.email}")
            // Change text of display
            homeTextLog.text =
                resources.getString(R.string.textView_home_subtext_is_connected, user.email)

            // Change btn name
            btnLogin.setText(R.string.logout)
            // change function to logout account function
            btnLogin.setOnClickListener {
                viewModel.logoutUser()
                recreate()
            }

        } else {
            // Set start login Activity
            btnLogin.setOnClickListener {
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            }
        }

        /**
         * Recipe drawing Part
         */

        val btnSearch:Button = findViewById(R.id.btn_search)

        // set listener on click event
        btnSearch.setOnClickListener {
            // get value of input search
            val inputSearch :EditText = findViewById(R.id.input_search)
            // set value to mainViewModel
            viewModel.searchVal = inputSearch.text.toString()

            // call searchRecipe
            viewModel.searchRecipe()

        }

        //Make observer for toast msg drawing
        viewModel.msgToast.observe(this@MainActivity) { message ->
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
        }

        /**
         * Check if recipe list as change and add callback
         * to open recipe activity when recipe card was clicked
         */
        viewModel.recipes.observe(this@MainActivity) { recipe ->
            val adapter = RecipeSearchListAdapter(recipe, object: OnItemClickListener {
                override fun onItemClick(position: Int) {
                    val intent = Intent(this@MainActivity, RecipeActivity::class.java)
                    intent.putExtra("recipeId", recipe.results[position].id)
                    startActivity(intent)
                }
            })
            recipeSearchList.adapter = adapter
        }
    }
}