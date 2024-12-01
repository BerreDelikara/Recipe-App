package com.example.recipeappstep1.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeappstep1.R
import com.example.recipeappstep1.fragment.RecipeDetailFragment
import com.example.recipeappstep1.model.Recipe


class RecipeListAdapter(
    private val recipes: List<Recipe>,
    private val onRecipeClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeListAdapter.ItemViewHolder>() {

    class ItemViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val recipeNameTextView: TextView = item.findViewById(R.id.recipeTitleTextView)
        private val recipeImageView: ImageView = item.findViewById(R.id.recipeImageView)

        private val RECIPE_KEY = "RECIPE"
        lateinit var recipe: Recipe

        init {
            item.setOnClickListener {
                val showDetailIntent = Intent(itemView.context, RecipeDetailFragment::class.java)
                showDetailIntent.putExtra(RECIPE_KEY, recipe)
            }
        }

        fun bind(recipe: Recipe) {
            this.recipe = recipe
            recipeNameTextView.text = recipe.strMeal

            Glide.with(itemView.context)
                .load(recipe.strMealThumb)
                .into(recipeImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.recipe_layout, parent, false)
        return ItemViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)

        holder.itemView.setOnClickListener {
            onRecipeClick(recipe)
        }
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

}
