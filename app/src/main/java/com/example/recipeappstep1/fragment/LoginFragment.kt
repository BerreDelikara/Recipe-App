package com.example.recipeappstep1.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.recipeappstep1.viewmodel.LoginViewModel
import com.example.recipeappstep1.R
import com.example.recipeappstep1.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        binding.viewModel = LoginViewModel()
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.isLoggedIn.observe(viewLifecycleOwner) { isLoggedIn ->
            if (isLoggedIn) {

                findNavController().navigate(R.id.action_loginFragment_to_categoryFragment)
            } else {

                Toast.makeText(context, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
