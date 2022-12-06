package com.example.cookrecipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
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

        if (viewModel.getUsers().getUserAuth() == null) {
            Log.d("Auth", "User not log")
        }
    }

    fun onClickCreateUser(view: View) {
        val result:String? = viewModel.dataValidator()
        // if null data is good for create new user
        if (result == null) {
            viewModel.registerUser()
        }else{
            Toast.makeText(baseContext,result,Toast.LENGTH_SHORT).show()
        }
        //Toast.makeText(baseContext,"User not add",Toast.LENGTH_SHORT).show()
    }
}