package com.example.cookrecipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.cookrecipe.databinding.ActivityRegisterBinding
import com.example.cookrecipe.view_model.RegisterViewModel


class RegisterActivity : AppCompatActivity() {

    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        // Set viewModelProvider
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        // Set binding modelView
        val binding: ActivityRegisterBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


    }

    fun onClick(view: View) {
        viewModel.registry()
    }

}