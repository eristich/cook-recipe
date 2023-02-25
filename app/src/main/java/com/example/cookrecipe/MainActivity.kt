package com.example.cookrecipe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cookrecipe.view_model.MainViewModel
import com.example.cookrecipe.view_model.MainViewModel.Companion.TAG

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set Main view model
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

    }

    override fun onResume() {
        super.onResume()

        //set view text
        val homeTextLog: TextView = findViewById(R.id.homeText2)

        //Set listener of btn
        val btnLogin: Button = findViewById(R.id.btn_login)

        val user = viewModel.getUser()

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
    }
}