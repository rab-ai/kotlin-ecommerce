package com.example.e_commerce.ui.screens.shop

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BagScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Bag") }) }
    ) { innerPadding ->
        Text(
            text = "Bag Screen",
            modifier = Modifier.padding(innerPadding),
            style = MaterialTheme.typography.headlineMedium
        )
    }
}