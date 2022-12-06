package com.example.cookrecipe.view_model

import android.util.Log
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {
    var email: String = ""
    var password: String = ""
    var passwordConf: String= ""

    fun registry() {
        if (password == passwordConf) {
            Log.d("Register", "password = $password")
        }
        Log.d("Register", "NOTpassword = $password and $passwordConf")
    }
}