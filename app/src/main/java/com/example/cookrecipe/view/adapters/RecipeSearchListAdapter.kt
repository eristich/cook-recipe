package com.example.cookrecipe.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cookrecipe.R
import com.example.cookrecipe.model.data.RecipeSearchResponse

interface OnItemClickListener {
    fun onItemClick(position: Int)
}

/**
 * This class is an adapter for recycler view of searched recipes
 */
class RecipeSearchListAdapter(private val itemList: RecipeSearchResponse, private val listener: OnItemClickListener) : RecyclerView.Adapter<RecipeSearchListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_minimal_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipeItem = itemList.results[position]
        holder.recipeName.text = recipeItem.title
        holder.itemView.setOnClickListener {
            listener.onItemClick(position)
        }
    }

    override fun getItemCount(): Int = itemList.results.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeName: TextView = itemView.findViewById(R.id.recipeName)
    }
}