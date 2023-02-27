package com.example.cookrecipe.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.AndroidViewModel
import com.example.cookrecipe.R
import com.example.cookrecipe.model.repo.FirebaseAuth


class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    private var firebaseAuth: FirebaseAuth = FirebaseAuth
    private val _msgToast = MutableLiveData<String>()
    var email: String = ""
    var password: String = ""
    var passwordConf: String = ""
    val msgToast: LiveData<String> get() = _msgToast

    var activityListener: ActivityListener? = null

    fun getUsers(): FirebaseAuth {
        return firebaseAuth
    }

    /**
     * Get data is valid for create new user
     * @return boolean true or false if bad data send
     */
    private fun dataValidator(): Boolean {
        Log.d(TAG, "registerUser: Data validator length of Password = " + this.password.length)
        return if (email.isEmpty()) {
            _msgToast.value =
                getApplication<Application>().getString(R.string.error_no_username)
            false
        } else if (password.isEmpty()) {
            _msgToast.value =
                getApplication<Application>().getString(R.string.error_no_password)
            false
        } else if (password.length < 6) {
            _msgToast.value =
                getApplication<Application>().getString(R.string.error_length_password)
            false
        } else if (password != passwordConf) {
            _msgToast.value =
                getApplication<Application>().getString(R.string.error_password_not_match)
            false
        } else {
            true
        }
    }

    /**
     * Create new user into db white firebase
     * @return Void
     */
    fun registerUser() {
        // Check data input
        if (this.dataValidator()) {

            val taskResult = firebaseAuth.createUser(email, password)

            taskResult.addOnCompleteListener { tasks ->
                Log.d(TAG, "registerUser: " + tasks.isSuccessful)
                if (tasks.isSuccessful) {
                    _msgToast.value =
                        getApplication<Application>().getString(R.string.success_sign_up)
                    activityClose()
                } else {
                    _msgToast.value =
                        getApplication<Application>().getString(R.string.error_sign_up)
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
    private fun activityClose() {
        activityListener?.onClose()
    }

    companion object {
        const val TAG: String = "Register-ModelView"
    }

}