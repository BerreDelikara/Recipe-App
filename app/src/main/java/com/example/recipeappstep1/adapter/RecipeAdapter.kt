package com.example.recipeappstep1.adapter

import android.view.ViewGroup

class RecipeAdapter(private val onClick: (Int) -> Unit) :
    ListAdapter<Recipe, RecipeAdapter.RecipeViewHolder>(RecipeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ListItemRecipeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

    class RecipeViewHolder(private val binding: ListItemRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: Recipe, onClick: (Int) -> Unit) {
            binding.recipe = recipe
            binding.root.setOnClickListener { onClick(recipe.id) }
        }
    }
}