package com.example.e_commerce.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.e_commerce.ui.screens.auth.LoginScreen
import com.example.e_commerce.ui.screens.auth.SignUpScreen

@Composable
fun AuthNavigation(navController: NavHostController, onLoginSuccess: () -> Unit) {
    NavHost(
        navController = navController,
        startDestination = Routes.SIGN_UP
    ) {
        composable(Routes.SIGN_UP) {
            SignUpScreen(
                onNavigateToLogin = { navController.navigate(Routes.LOGIN) },
                onSignUpSuccess = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.SIGN_UP) { inclusive = true }
                    }
                }
            )
        }
        composable(Routes.LOGIN) {
            LoginScreen(
                onNavigateToSignUp = { navController.navigate(Routes.SIGN_UP) },
                onLoginSuccess = onLoginSuccess
            )
        }
    }
}