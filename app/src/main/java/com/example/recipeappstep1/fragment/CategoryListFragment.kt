package com.example.recipeappstep1.fragment

import Parser
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeappstep1.R
import com.example.recipeappstep1.adapter.CategoryAdapter
import com.example.recipeappstep1.databinding.FragmentCategoryListBinding

class CategoryListFragment : Fragment() {
    private lateinit var binding: FragmentCategoryListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentCategoryListBinding>(inflater, R.layout.fragment_category_list, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.categoryRecyclerView
        recyclerView.adapter = CategoryAdapter(Parser().parseCategories())
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = CategoryAdapter(Parser().parseCategories()) }

    }
}
