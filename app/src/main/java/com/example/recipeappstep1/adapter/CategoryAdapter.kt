package com.example.recipeappstep1.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeappstep1.R
import com.example.recipeappstep1.fragment.RecipeListFragment
import com.example.recipeappstep1.model.Category

class CategoryAdapter(private val categories: List<Category>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryTextView: TextView = itemView.findViewById(R.id.categoryNameTextView)
        private lateinit var category: Category

        private val CATEGORY_KEY = "CATEGORY"

        init {
            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, RecipeListFragment::class.java)
                intent.putExtra(CATEGORY_KEY, category)
                context.startActivity(intent)
            }
        }

        fun bind(category: Category) {
            this.category = category
            categoryTextView.text = category.strCategory
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.category_layout, parent, false)
        return CategoryViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}
