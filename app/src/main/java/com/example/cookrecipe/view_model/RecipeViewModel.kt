package com.example.cookrecipe.view_model

import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.AndroidViewModel
import com.example.cookrecipe.R
import com.example.cookrecipe.view.RecipeActivity

class RecipeViewModel(application: Application) : AndroidViewModel(application) {

    private var title: String = ""
    private var url: String = ""

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

    companion object {
        const val TAG: String = "Recipe-ViewModel"
    }

}