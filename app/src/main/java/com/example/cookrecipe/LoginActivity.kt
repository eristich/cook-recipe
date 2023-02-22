package com.example.cookrecipe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.cookrecipe.databinding.ActivityLoginBinding
import com.example.cookrecipe.view_model.LoginViewModel


class LoginActivity : AppCompatActivity(), LoginViewModel.ActivityListener {

    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // set view model
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        // Set instance of interface of activity
        viewModel.activityListener = this@LoginActivity

        // Set binding modelView
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this@LoginActivity, R.layout.activity_login)
        binding.loginViewModel = viewModel
        binding.lifecycleOwner = this@LoginActivity

        // Set on click listener for btn Register
        val btnRegister: Button = findViewById(R.id.btn_register)
        btnRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        // Set on click listener for btn Register
        val btnLogin: Button = findViewById(R.id.btn_login)
        btnLogin.setOnClickListener {
           viewModel.loginUser()
        }

        // Get message toast emit in live data
        viewModel.msgToast.observe(this@LoginActivity) { message ->
            Toast.makeText(this@LoginActivity, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClose() {
        finish()
    }
}