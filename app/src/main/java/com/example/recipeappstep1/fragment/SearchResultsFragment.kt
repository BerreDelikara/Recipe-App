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
import com.example.recipeappstep1.databinding.FragmentSearchResultsBinding
import com.example.recipeappstep1.viewmodel.SearchResultsViewModel

class SearchResultsFragment : Fragment() {

    private val viewModel: SearchResultsViewModel by activityViewModels()
    private val args: SearchResultsFragmentArgs by navArgs()
    private lateinit var binding: FragmentSearchResultsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_results, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.recipes.observe(viewLifecycleOwner) { recipes ->
            binding.recyclerView.apply {
                val adapter = RecipeListAdapter(recipes) { recipe ->
                    val action =
                        SearchResultsFragmentDirections.actionSearchResultsFragmentToRecipeDetailsFragment(
                            recipe.idMeal
                        )
                    if (recipe.idMeal != -1) {
                        findNavController().navigate(action)
                    }
                }
                layoutManager = LinearLayoutManager(requireContext())
                this.adapter = adapter
            }
        }

        viewModel.fetchRecipes(args.searchQuery)
    }
}