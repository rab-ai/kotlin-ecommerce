package com.example.e_commerce.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.e_commerce.ui.screens.home.HomeScreen
import com.example.e_commerce.ui.screens.profile.ProfileScreen
import com.example.e_commerce.ui.screens.shop.BagScreen
import com.example.e_commerce.ui.screens.shop.FavoritesScreen

@Composable
fun MainNavigation(navController: NavHostController) {
    val bottomNavController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(bottomNavController) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            NavHost(
                navController = bottomNavController,
                startDestination = Routes.HOME,
                modifier = Modifier.fillMaxSize()
            ) {
                composable(Routes.HOME) { HomeScreen(bottomNavController) }
                composable(Routes.SHOP) { ShopNavigation(bottomNavController) }
                composable(Routes.BAG) { BagScreen() }
                composable(Routes.FAVORITES) { FavoritesScreen(bottomNavController) }
                composable(Routes.PROFILE) { ProfileScreen() }
            }
        }
    }
}
