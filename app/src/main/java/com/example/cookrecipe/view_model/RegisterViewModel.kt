package com.example.cookrecipe.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookrecipe.model.repo.UsersRepository
import kotlinx.coroutines.launch


class RegisterViewModel : ViewModel() {
    var email: String = ""
    var password: String = ""
    var passwordConf: String = ""
    private var usersRepository: UsersRepository = UsersRepository()

    fun getUsers(): UsersRepository {
        return usersRepository
    }

    /**
     * Get data is valid for create new user
     * @return String or null
     */
    fun dataValidator():String?{
        if (email.isEmpty()){
            return "No user name entered"
        }else if (password.isEmpty()){
            return "No password entered"
        }else if (password != passwordConf){
            return "The passwords, do not match"
        }
        return null
    }

    /**
     * create new user into db
     */
     fun registerUser() {
        viewModelScope.launch{
            if (usersRepository.createUser(email, password)) {
                Log.d(TAG, "Success")
            } else {
                Log.d(TAG, "FAIL !")
            }
        }
    }
    companion object{
         const val TAG:String = "Register-ModelView"
    }

}