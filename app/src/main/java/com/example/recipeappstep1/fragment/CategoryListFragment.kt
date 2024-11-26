package com.example.recipeappstep1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeappstep1.R
import com.example.recipeappstep1.adapter.CategoryAdapter
import com.example.recipeappstep1.databinding.FragmentCategoryListBinding
import com.example.recipeappstep1.databinding.FragmentLoginBinding
import com.example.recipeappstep1.network.ApiCall
import com.example.recipeappstep1.viewmodel.CategoryViewModel
import com.example.recipeappstep1.viewmodel.LoginViewModel

class CategoryListFragment : Fragment() {
    private lateinit var binding: FragmentCategoryListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentCategoryListBinding>(inflater, R.layout.fragment_category_list, container, false)
        val recyclerView = binding.categoryRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        binding.viewModel = CategoryViewModel()
        binding.lifecycleOwner = viewLifecycleOwner


        recyclerView.adapter = CategoryAdapter(ApiCall().getCategories())
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args = GameFragmentArgs.fromBundle(requireArguments())
        binding.nameTextView.text = args.playerName
        super.onViewCreated(view, savedInstanceState)
    }
}
