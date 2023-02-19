package com.example.cookrecipe.model.repo

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UsersRepository {
    private var auth: FirebaseAuth = Firebase.auth

    fun getUserAuth(): FirebaseUser? {
        return auth.currentUser
    }

    fun createUser(email: String, password: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }

   /* companion object {
        const val TAG = "UserRepo"
    }*/
}
