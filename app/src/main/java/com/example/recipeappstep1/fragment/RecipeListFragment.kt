package com.example.recipeappstep1.fragment

import Parser
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.recipeappstep1.R
import com.example.recipeappstep1.adapter.RecipeListAdapter
import com.example.recipeappstep1.databinding.FragmentRecipeListBinding
import com.example.recipeappstep1.viewmodel.RecipeListViewModel

class RecipeListFragment : Fragment() {

    private val recipeViewModel: RecipeListViewModel by activityViewModels()
    private val args: RecipeListFragmentArgs by navArgs()
    private lateinit var binding: FragmentRecipeListBinding
    private lateinit var recipeListAdapter: RecipeListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recipe_list, container, false)

        binding.viewModel = recipeViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        recipeListAdapter = RecipeListAdapter(Parser().parseAllRecipesInCategory(args.categoryName.toString(),this.context))
        binding.recyclerView.adapter = recipeListAdapter

//        recipeViewModel.recipes.observe(viewLifecycleOwner, Observer { recipes ->
//            recipes?.let {
//                binding.viewModel.fetchRecipes(args.categoryName)
//            }
//        })

        return binding.root
    }
}