package com.example.recipeappstep1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.recipeappstep1.R
import com.example.recipeappstep1.databinding.FragmentSearchRecipesBinding


class SearchRecipesFragment : Fragment() {

    private lateinit var binding: FragmentSearchRecipesBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_recipes, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchButton.setOnClickListener {
            val key = binding.searchEditText.text.toString()
            findNavController().navigate(SearchRecipesFragmentDirections.actionSearchRecipesFragmentToSearchResultsFragment(key))
        }

    }


}
