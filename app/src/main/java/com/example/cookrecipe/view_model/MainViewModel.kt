package com.example.cookrecipe.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.cookrecipe.model.repo.UsersRepository
import com.google.firebase.auth.FirebaseUser


class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var usersRepository: UsersRepository = UsersRepository()

    /**
     * Return FireBase User
     */
    fun getUser(): FirebaseUser? {
        return usersRepository.getUserAuth()
    }

    /**
     * Disconnect user
     */
    fun logoutUser(){
        usersRepository.logoutUser()
    }



    companion object{
        // define TAG of console
        const val TAG: String = "Main-ModelView"
    }
}