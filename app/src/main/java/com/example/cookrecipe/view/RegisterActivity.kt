package com.example.cookrecipe.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.cookrecipe.R
import com.example.cookrecipe.databinding.ActivityRegisterBinding
import com.example.cookrecipe.view_model.RegisterViewModel


class RegisterActivity : AppCompatActivity(), RegisterViewModel.ActivityListener {

    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Set viewModelProvider
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        // Set instance of interface of activity
        viewModel.activityListener = this

        // Set binding modelView
        val binding: ActivityRegisterBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        if (viewModel.getUsers().getUserAuth() == null) {
            Log.d("Auth", "User not log")
        }

        val btnRegister: Button = findViewById(R.id.btn_register)
        btnRegister.setOnClickListener {
            viewModel.registerUser() //Create new user
        }

        val btnLogin: Button = findViewById(R.id.btn_login)
        btnLogin.setOnClickListener {
            finish() // end of activity return on login activity
        }

        viewModel.msgToast.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

    }

    override fun onClose() {
        finish()
    }
}