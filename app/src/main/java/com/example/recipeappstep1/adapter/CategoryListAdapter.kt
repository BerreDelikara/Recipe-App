package com.example.recipeappstep1.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeappstep1.R
import com.example.recipeappstep1.fragment.RecipeDetailFragment
import com.example.recipeappstep1.model.Category

class CategoryListAdapter(
    private val categories: List<Category>,
    private val onCategoryClick: (Category) -> Unit
) : ListAdapter<Category, CategoryListAdapter.CategoryViewHolder>(CategoryDiffCallback()) {

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryTextView: TextView = itemView.findViewById(R.id.categoryNameTextView)
        private lateinit var category: Category
        private val categoryImageView: ImageView = itemView.findViewById(R.id.categoryImageView)

        private val CATEGORY_KEY = "CATEGORY"

        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, RecipeDetailFragment::class.java)
                intent.putExtra(CATEGORY_KEY, category)
            }
        }

        fun bind(category: Category) {
            this.category = category
            categoryTextView.text = category.strCategory

            Glide.with(itemView.context)
                .load(category.strCategoryThumb)
                .into(categoryImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.category_layout, parent, false)
        return CategoryViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)
        holder.itemView.setOnClickListener {
            onCategoryClick(category)
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}
class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.strCategory == newItem.strCategory
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}
