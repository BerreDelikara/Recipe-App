package com.example.recipeappstep1.fragment

import Parser
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeappstep1.R
import com.example.recipeappstep1.adapter.CategoryAdapter
import com.example.recipeappstep1.databinding.FragmentCategoryListBinding
import com.example.recipeappstep1.viewmodel.CategoryViewModel

class CategoryListFragment : Fragment() {
    private lateinit var binding: FragmentCategoryListBinding
    private val viewModel: CategoryViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentCategoryListBinding>(inflater, R.layout.fragment_category_list, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = binding.categoryRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        recyclerView.adapter = CategoryAdapter(Parser().parseCategories())
    }
}
