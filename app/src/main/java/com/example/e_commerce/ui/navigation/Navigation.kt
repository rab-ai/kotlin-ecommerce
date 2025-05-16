package com.example.e_commerce.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import com.example.e_commerce.utils.SessionManager

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }

    var isLoggedIn by remember { mutableStateOf(sessionManager.isLoggedIn()) }

    if (!isLoggedIn) {
        AuthNavigation(navController, onLoginSuccess = { isLoggedIn = true })
    } else {
        MainNavigation(navController)
    }
}