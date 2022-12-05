package com.example.cookrecipe

import android.content.Intent
import android.os.Bundle
import android.service.autofill.OnClickAction
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

    fun mainView(view: View) {
        // navigation to Main activity
        this.finish()
    }


}