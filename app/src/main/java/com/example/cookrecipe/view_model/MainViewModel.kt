package com.example.cookrecipe.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel


class MainViewModel(application: Application) : AndroidViewModel(application) {

    companion object{
        // define TAG of console
        const val TAG: String = "Main-ModelView"
    }
}