package com.example.cookrecipe.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.cookrecipe.model.repo.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var firebaseAuth: FirebaseAuth = FirebaseAuth

    /**
     * Return FireBase User
     */
    fun getUser(): FirebaseUser? {
        return firebaseAuth.getUserAuth()
    }

    /**
     * Disconnect user
     */
    fun logoutUser() {
        firebaseAuth.logoutUser()
    }

    companion object {
        // define TAG of console
        const val TAG: String = "Main-ModelView"
    }
}