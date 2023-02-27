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
import com.example.cookrecipe.R
import com.example.cookrecipe.databinding.ActivityMainBinding
import com.example.cookrecipe.view_model.MainViewModel
import com.example.cookrecipe.view_model.MainViewModel.Companion.TAG

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

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

        /*TODO use viewModel.recipe an observer */

    }
}