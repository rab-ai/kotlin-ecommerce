package com.example.e_commerce.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.e_commerce.R

data class BottomNavItem(val label: String, val route: String, val icon: Int, val filledIcon: Int)

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("Home", Routes.HOME, R.drawable.home_icon, R.drawable.home_icon_filled),
        BottomNavItem("Shop", Routes.SHOP, R.drawable.shop_icon, R.drawable.shop_icon_filled),
        BottomNavItem("Bag", Routes.BAG, R.drawable.bag_icon, R.drawable.bag_icon_filled),
        BottomNavItem("Favorites", Routes.FAVORITES, R.drawable.favorites_icon, R.drawable.favorites_icon_filled),
        BottomNavItem("Profile", Routes.PROFILE, R.drawable.profile_icon, R.drawable.profile_icon_filled)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            .background(MaterialTheme.colorScheme.onPrimary)
    ) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.fillMaxWidth()
        ) {
            val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

            items.forEach { item ->
                val isSelected = currentRoute == item.route

                NavigationBarItem(
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
                        indicatorColor = Color.Transparent
                    ),
                    label = {
                        Text(
                            text = item.label,
                            color = if (isSelected) Color.Red else Color.Gray,
                            fontSize = 12.sp
                        )
                    },
                    selected = isSelected,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(Routes.HOME) { inclusive = false }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        val iconResource = if (isSelected) item.filledIcon else item.icon
                        Icon(
                            painter = painterResource(iconResource),
                            contentDescription = item.label,
                            modifier = Modifier.size(30.dp),
                            tint = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray
                        )
                    }
                )
            }
        }
    }
}
