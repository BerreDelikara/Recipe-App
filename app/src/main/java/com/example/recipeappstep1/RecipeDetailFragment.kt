package com.example.recipeappstep1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.recipeappstep1.databinding.FragmentRecipeDetailBinding
import com.example.recipeappstep1.viewmodel.RecipeViewModel

class RecipeDetailFragment : Fragment() {
    private lateinit var viewModel: RecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentRecipeDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_recipe_detail, container, false
        )
        viewModel = ViewModelProvider(requireActivity())[RecipeViewModel::class.java]
        viewModel.selectedRecipe.observe(viewLifecycleOwner) { recipe ->
            binding.recipe = recipe
        }
        return binding.root
    }
}