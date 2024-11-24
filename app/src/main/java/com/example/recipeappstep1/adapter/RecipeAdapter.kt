package com.example.recipeappstep1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
import com.example.recipeappstep1.model.Recipe
import kotlinx.android.synthetic.main.item_recipe.view.*

class RecipeAdapter(
    private val recipes: List<Recipe>,
    private val onItemClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    // ViewHolder holds the reference to the view elements
    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(recipe: Recipe) {
            itemView.recipeName.text = recipe.name
            itemView.recipeDescription.text = recipe.description
            Glide.with(itemView.context)
                .load(recipe.imageUrl) // Load image using Glide
                .into(itemView.recipeImage)

            // Set click listener to trigger the onItemClick lambda
            itemView.setOnClickListener {
                onItemClick(recipe)
            }
        }
    }

    // Creates a new ViewHolder for the recipe item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_layout, parent, false)
        return RecipeViewHolder(view)
    }

    // Binds data to the ViewHolder
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)
    }

    // Return the number of items in the list
    override fun getItemCount(): Int = recipes.size
}
