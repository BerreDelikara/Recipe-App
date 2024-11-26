package com.example.recipeappstep1.fragment

import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeappstep1.R

class CategoryListFragment : Fragment() {
    override fun onCreateView(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.fragment_category_list)

        val recyclerView = findViewById<RecyclerView>(R.id.category_layout)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val listOfCharacters = createCharactersList(13)

        recyclerView.adapter = CharacterListAdapter(listOfCharacters)
    }
}
