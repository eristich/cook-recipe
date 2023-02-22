package com.example.cookrecipe.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cookrecipe.R
import com.example.cookrecipe.model.repo.UsersRepository

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private var usersRepository: UsersRepository = UsersRepository()
    private val mutableMsgToast = MutableLiveData<String>()
    val msgToast: LiveData<String> get() = mutableMsgToast
    var activityListener: ActivityListener? = null
    var email: String = ""
    var password: String = ""

    /**
     * Get data is valid for create new user
     * @return boolean true or false if bad data send
     */
    private fun dataValidator(): Boolean {
        Log.d(RegisterViewModel.TAG, "registerUser: Data validator length of Password = " + this.password.length)
        return if (email.isEmpty()) {
            mutableMsgToast.value =
                getApplication<Application>().getString(R.string.error_no_username)
            false
        } else if (password.isEmpty()) {
            mutableMsgToast.value =
                getApplication<Application>().getString(R.string.error_no_password)
            false
        } else if (password.length < 6) {
            mutableMsgToast.value =
                getApplication<Application>().getString(R.string.error_length_password)
            false
        } else {
            true
        }
    }

    /**
     * Log user white firebase
     * @return Void
     */
    fun loginUser() {
        // Check data input
        if (this.dataValidator()) {

            val taskResult = usersRepository.loginUser(email, password)

            taskResult.addOnCompleteListener { tasks ->
                Log.d(RegisterViewModel.TAG, "LoginUser: " + tasks.isSuccessful)
                if (tasks.isSuccessful) {
                    mutableMsgToast.value =
                        getApplication<Application>().getString(R.string.success_sign_in)
                    activityClose()
                } else {
                    mutableMsgToast.value =
                        getApplication<Application>().getString(R.string.error_sign_in)
                }
            }
        }
    }

    /**
     * Set event for closing activity
     */
    interface ActivityListener {
        fun onClose()
    }

    /**
     * Close the current activity
     */
    fun activityClose() {
        activityListener?.onClose()
    }
    companion object{
        // define TAG of console
        // const val TAG: String = "Main-ModelView"
    }
}