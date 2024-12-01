package com.example.recipeappstep1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeappstep1.R
import com.example.recipeappstep1.adapter.RecipeListAdapter
import com.example.recipeappstep1.databinding.FragmentFavoriteRecipesBinding
import com.example.recipeappstep1.viewmodel.RecipeListViewModel

class FavoriteRecipesFragment : Fragment() {

    private val recipeListViewModel: RecipeListViewModel by activityViewModels()
    private val args: RecipeListFragmentArgs by navArgs()
    private lateinit var binding: FragmentFavoriteRecipesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recipe_list, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipeListViewModel.recipes.observe(viewLifecycleOwner) { recipes ->
            binding.recyclerView.apply {
                val adapter = RecipeListAdapter(recipes) { recipe ->
                    val action =
                        RecipeListFragmentDirections.actionRecipeListFragmentToRecipeDetailFragment(
                            recipe.idMeal
                        )
                    findNavController().navigate(action)
                }
                layoutManager = LinearLayoutManager(requireContext())
                this.adapter = adapter
            }
        }

        recipeListViewModel.fetchRecipes(args.categoryName)
    }
}