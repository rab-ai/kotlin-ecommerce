package com.example.e_commerce.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.e_commerce.ui.screens.category.CategoryDetailScreen
import com.example.e_commerce.ui.screens.category.CategoryItem
/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(navController: NavController) {
    val categories = listOf("Elektronik", "Moda", "Ev & Yaşam", "Kitap", "Spor", "Oyuncak")

    Scaffold(
        topBar = { TopAppBar(title = { Text("Kategoriler") }) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(categories) { category ->
                CategoryItem(category) {
                    navController.navigate("categoryDetail/$category")
                }
            }
        }
    }
}
@Composable
fun CategoryNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "categoryList"
    ) {
        composable("categoryList") { CategoryScreen(navController) }
        composable("category_detail/{parentCategory}/{categoryName}") { backStackEntry ->
            val parentCategory = backStackEntry.arguments?.getString("parentCategory") ?: "Unknown"
            val categoryName = backStackEntry.arguments?.getString("categoryName") ?: "Unknown"

            CategoryDetailScreen(parentCategory, categoryName, navController)
        }

    }
}
*/