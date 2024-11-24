package com.example.recipeappstep1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.recipeappstep1.adapter.RecipeAdapter
import com.example.recipeappstep1.viewmodel.RecipeViewModel

class RecipeFragment : Fragment() {
    private lateinit var viewModel: RecipeViewModel
    private lateinit var adapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentRecipeListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_recipe_list, container, false
        )
        viewModel = ViewModelProvider(requireActivity())[RecipeViewModel::class.java]
        adapter = RecipeAdapter { recipeId -> viewModel.selectRecipe(recipeId) }

        binding.recyclerView.adapter = adapter
        viewModel.recipes.observe(viewLifecycleOwner) { recipes ->
            adapter.submitList(recipes)
        }
        return binding.root
    }
}

/*
class GameFragment: Fragment(){
    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentGameBinding>(inflater, R.layout.fragment_game, container, false)

        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args = GameFragmentArgs.fromBundle(requireArguments())
        binding.nameTextView.text = args.playerName
        super.onViewCreated(view, savedInstanceState)
    }
}
* */