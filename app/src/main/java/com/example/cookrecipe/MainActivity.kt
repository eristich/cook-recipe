package com.example.cookrecipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

//    fun handleLogin(view: View){
//        val loginActivity = Intent(this@MainActivity,LoginActivity::class.java)
//        startActivity(loginActivity)
//    }
}