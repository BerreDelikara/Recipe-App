package com.example.recipeappstep1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.recipeappstep1.R
import com.example.recipeappstep1.databinding.FragmentRecipeDetailBinding
import com.example.recipeappstep1.viewmodel.FavoriteRecipesViewModel
import com.example.recipeappstep1.viewmodel.RecipeViewModel


class RecipeDetailFragment : Fragment() {
    private val favoriteRecipesViewModel: FavoriteRecipesViewModel by viewModels()
    private val viewModel: RecipeViewModel by viewModels()
    private lateinit var binding: FragmentRecipeDetailBinding
    private val args: RecipeDetailFragmentArgs by navArgs()
    private var isFavorite = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_recipe_detail, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchRecipe(args.recipeId)

        viewModel.recipe.observe(viewLifecycleOwner) { recipe ->
            recipe?.let {
                binding.recipe = recipe
                binding.recipeIngredientsTextView.text = recipe.getIngredients()
                Glide.with(this)
                    .load(it.strMealThumb)
                    .into(binding.recipeImageView)


                favoriteRecipesViewModel.getFavorites()
                favoriteRecipesViewModel.recipes.observe(viewLifecycleOwner) { favorites ->
                    isFavorite = favorites.any { it.idMeal == recipe.idMeal }
                    binding.favoriteToggle.isChecked = isFavorite
                }
        }
            binding.favoriteToggle.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    favoriteRecipesViewModel.saveFavorite(args.recipeId.toString())
                } else {
                    favoriteRecipesViewModel.removeFavorite(args.recipeId.toString())
                }
            }
        }
    }

}