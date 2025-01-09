package com.example.recipeappstep1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeappstep1.adapter.CategoryListAdapter
import com.example.recipeappstep1.databinding.FragmentCategoryListBinding
import com.example.recipeappstep1.viewmodel.CategoryViewModel

class CategoryListFragment : Fragment() {
    private lateinit var binding: FragmentCategoryListBinding
    private val viewModel: CategoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.categories.observe(viewLifecycleOwner) { categories ->
            val adapter = categories?.let {
                CategoryListAdapter(it) { selectedCategory ->
                    findNavController().navigate(
                        CategoryListFragmentDirections.actionCategoryListFragmentToRecipeListFragment(
                            selectedCategory.strCategory
                        )
                    )
                }
            }

            binding.categoryRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                this.adapter = adapter
            }
        }

        viewModel.fetchCategories()
    }
}
