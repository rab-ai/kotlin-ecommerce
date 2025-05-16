package com.example.e_commerce.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.e_commerce.ui.screens.category.CategoryDetailScreen
import com.example.e_commerce.ui.screens.shop.ShopScreen

object ShopRoutes {
    const val SHOP_MAIN = "shop_main"
    const val CATEGORY_DETAIL = "category_detail/{parentCategory}/{categoryName}"
}

@Composable
fun ShopNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    val shopNavController = rememberNavController()

    NavHost(
        navController = shopNavController,
        startDestination = ShopRoutes.SHOP_MAIN,
        modifier = modifier
    ) {
        composable(ShopRoutes.SHOP_MAIN) { ShopScreen(shopNavController) }

        composable("category_detail/{parentCategory}/{categoryName}") { backStackEntry ->
            val parentCategory = backStackEntry.arguments?.getString("parentCategory") ?: "Unknown"
            val categoryName = backStackEntry.arguments?.getString("categoryName") ?: "Unknown"

            CategoryDetailScreen(parentCategory, categoryName, navController)
        }
    }
}
