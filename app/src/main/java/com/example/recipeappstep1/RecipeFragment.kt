package com.example.recipeappstep1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeappstep1.adapter.RecipeAdapter
import com.example.recipeappstep1.databinding.FragmentRecipeListBinding
import com.example.recipeappstep1.viewmodel.RecipeViewModel

class RecipeFragment : Fragment() {

    private val recipeViewModel: RecipeViewModel by activityViewModels()
    private lateinit var binding: FragmentRecipeListBinding
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recipe_list, container, false)

        binding.viewModel = recipeViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        recipeAdapter = RecipeAdapter(mutableListOf())
        binding.recyclerView.adapter = recipeAdapter

        recipeViewModel.recipes.observe(viewLifecycleOwner, Observer { recipes ->
            recipes?.let {
                recipeAdapter.updateRecipes(it)
            }
        })

        return binding.root
    }
}


/*class GameFragment: Fragment(){
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