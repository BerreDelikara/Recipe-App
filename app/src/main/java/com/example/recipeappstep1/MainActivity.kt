package com.example.recipeappstep1

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.recipeappstep1.viewmodel.LoginViewModel
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawer_layout)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(setOf(R.id.searchRecipesFragment,R.id.categoryListFragment,
         R.id.favoriteRecipesFragment, //will be implemented later
            R.id.loginFragment), drawerLayout)

        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        NavigationUI.setupWithNavController(navigationView, navController)

        navigationView.setNavigationItemSelectedListener { menuItem ->
            handleNavigationItemSelected(menuItem)
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun handleNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.nav_search -> {
                navController.navigate(R.id.searchRecipesFragment)
            }
            R.id.nav_recipes -> {
                navController.navigate(R.id.categoryListFragment)
            }
            R.id.nav_favorites -> {
            // will be implemented later
            navController.navigate(R.id.favoriteRecipesFragment)
           }
            R.id.nav_logout -> {
                val viewModel: LoginViewModel by viewModels()
                viewModel.logout()

                navController.navigate(R.id.loginFragment)
                Toast.makeText(baseContext,"Logged out of account.", Toast.LENGTH_SHORT).show()
            }
        }
        drawerLayout.closeDrawers()
        return true
    }


}