package com.example.cookrecipe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun handleLogin(view: View){
        val loginActivity = Intent(this@MainActivity,LoginActivity::class.java)
        startActivity(loginActivity)
    }


}