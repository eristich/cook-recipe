package com.example.cookrecipe.view_model

import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cookrecipe.R
import com.example.cookrecipe.model.data.Recipe
import com.example.cookrecipe.model.interfaces.SpoonacularRecipe
import com.example.cookrecipe.model.repo.SpoonacularRecipeAPI
import com.example.cookrecipe.view.RecipeActivity
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RecipeViewModel(application: Application) : AndroidViewModel(application) {

    var recipeId: Int = 1
    var title: String = ""
    var url: String = ""
    private var spoonacularRecipe: SpoonacularRecipe =
        SpoonacularRecipeAPI.createService(SpoonacularRecipe::class.java)

    private var _recipe = MutableLiveData<Recipe>()
    val recipe: LiveData<Recipe> = _recipe

    /**
     * Call send data to other user
     * @param context of application (this@RecipeActivity)
     */
    fun shareRecipe(context: RecipeActivity) {
        Log.d(TAG, "shareRecipe: startup now")
        val sendIntent: Intent = Intent(Intent.ACTION_SEND).apply {
            putExtra(
                Intent.EXTRA_TEXT,
                getApplication<Application>().getString(R.string.content_sharing, title, url)
            )
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        Log.d(TAG, "shareRecipe: startup now")
        startActivity(context, shareIntent, null)
    }

    fun getRecipe() {
        runBlocking {
            launch {
                val response = spoonacularRecipe.getThisRecipe(recipeId)
                if (response.isSuccessful) {
                    _recipe.value = response.body()
                    Log.d(TAG, "getRecipe: ${response.body()?.sourceUrl}")
                } else {
                    Log.i(TAG, "getRecipe: Http error ${response.code()}")
                }
            }
        }
    }

    companion object {
        const val TAG: String = "Recipe-ViewModel"
    }

}