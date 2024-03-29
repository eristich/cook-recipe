package com.example.cookrecipe.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cookrecipe.R
import com.example.cookrecipe.model.data.RecipeSearchResponse
import com.example.cookrecipe.model.interfaces.SpoonacularSearch
import com.example.cookrecipe.model.repo.FirebaseAuth
import com.example.cookrecipe.model.repo.SpoonacularRecipeAPI
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var firebaseAuth: FirebaseAuth = FirebaseAuth

    //Define Api interface
    private var spoonacularSearch: SpoonacularSearch =
        SpoonacularRecipeAPI.createService(SpoonacularSearch::class.java)

    // Create canal of recipe
    private val _recipe = MutableLiveData<RecipeSearchResponse>()

    // The recipe list
    val recipes: LiveData<RecipeSearchResponse> = _recipe

    // Val of Search input
    var searchVal: String = ""

    // Make toast live data
    private val _msgToast = MutableLiveData<String>()
    val msgToast: LiveData<String> get() = _msgToast


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

    /**
     * Search Recipe
     */
    fun searchRecipe() {

        Log.d(TAG, "searchRecipe: $searchVal")

        if (searchVal.isNotEmpty()) {

            CoroutineScope(Dispatchers.IO).launch {
                Log.d(TAG, "searchRecipe: LAUNCH COROUTINE")

                val response = spoonacularSearch.searchRecipe(searchVal, 30)

                if (response.isSuccessful) {
                    _recipe.postValue(response.body())
                    Log.d(TAG, "searchRecipe:  SUCCESS ${response.body()}")
                } else {
                    Log.d(TAG, "searchRecipe: http code ${response.code()}")
                }
            }

        } else {
            _msgToast.value =
                getApplication<Application>().getString(R.string.error_search_empty_string)
        }

    }


    companion object {
        // define TAG of console
        const val TAG: String = "Main-ModelView"
    }
}