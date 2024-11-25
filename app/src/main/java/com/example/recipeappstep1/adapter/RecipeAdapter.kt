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
import com.example.recipeappstep1.RecipeDetailFragment
import com.example.recipeappstep1.model.Recipe


class RecipeAdapter(private val data: MutableList<Recipe>) : RecyclerView.Adapter<RecipeAdapter.ItemViewHolder>() {

    class ItemViewHolder(val item: View) : RecyclerView.ViewHolder(item) {
        private val recipeNameTextView: TextView = item.findViewById(R.id.recipeTitleTextView)
        private val recipeCategoryTextView: TextView = item.findViewById(R.id.recipeCategoryTextView)
        private val recipeImageView: ImageView = item.findViewById(R.id.recipeImageView)

        private val RECIPE_KEY = "RECIPE"
        lateinit var recipe: Recipe

        init {
            item.setOnClickListener {
                val showDetailIntent = Intent(itemView.context, RecipeDetailFragment::class.java)
                showDetailIntent.putExtra(RECIPE_KEY, recipe)
                itemView.context.startActivity(showDetailIntent)
            }
        }

        fun bind(recipe: Recipe) {
            this.recipe = recipe
            recipeNameTextView.text = recipe.strMeal
            recipeCategoryTextView.text = recipe.strCategory

            Glide.with(itemView.context)
                .load(recipe.strMealThumb)
                .placeholder(R.drawable.placeholder)
                .into(recipeImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.recipe_layout, parent, false)
        return ItemViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val recipe = data[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
