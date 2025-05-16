package com.example.e_commerce.ui.screens.shop

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.KeyboardArrowLeft
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.e_commerce.ui.navigation.Routes
import com.example.e_commerce.ui.screens.category.CategoryData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopTopAppBar(onBackClick: () -> Unit, onSearchClick: () -> Unit) {
    TopAppBar(
        title = { CenteredTitle("Categories") },
        navigationIcon = { IconButton(onClick = onBackClick) { Icon(Icons.AutoMirrored.Sharp.KeyboardArrowLeft, "Back") } },
        actions = { IconButton(onClick = onSearchClick) { Icon(Icons.Sharp.Search, "Search") } },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.onPrimary)
    )
}

@Composable
fun CenteredTitle(text: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(text = text, style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
fun ShopScreen(navController: NavHostController) {
    Scaffold(
        topBar = { ShopTopAppBar(onBackClick = { navController.navigate(Routes.HOME) }, onSearchClick = {}) },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding())
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MainCategoryScreen(onCategoryClick = { categoryName, parentCategory  ->
                navController.navigate("category_detail/$parentCategory/$categoryName")
            })
        }
    }
}

@Composable
fun MainCategoryScreen(onCategoryClick: (String, String) -> Unit) {
    var selectedTab by remember { mutableStateOf("Women") }
    val listState = rememberLazyListState()

    val categories by remember(selectedTab) { mutableStateOf(CategoryData.getCategoriesForTab(selectedTab)) }

    LaunchedEffect(selectedTab) {
        listState.scrollToItem(0)
    }

    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        CategoryTabs(selectedTab, onTabSelected = { selectedTab = it })
        LazyColumn(state = listState) {
            item { SalesBanner() }
            items(categories) { (category, imageRes) ->
                CategoryItem(category, imageRes) { onCategoryClick(category, selectedTab) }
            }
        }
    }
}


@Composable
fun CategoryTabs(selectedTab: String, onTabSelected: (String) -> Unit) {
    TabRow(
        selectedTabIndex = listOf("Women", "Men", "Kids").indexOf(selectedTab),
        containerColor = Color.White,
        modifier = Modifier.height(44.dp)
    ) {
        listOf("Women", "Men", "Kids").forEach { tabName ->
            Tab(
                selected = selectedTab == tabName,
                onClick = { onTabSelected(tabName) },
                modifier = Modifier.height(44.dp)
            ) {
                Text(tabName, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = Color.Black)
            }
        }
    }
}

@Composable
fun CategoryList(categories: List<Pair<String, Int>>, listState: LazyListState, onCategoryClick: (String) -> Unit) {
    LazyColumn(state = listState) {
        item { SalesBanner() }
        items(categories) { (category, imageRes) -> CategoryItem(category, imageRes) { onCategoryClick(category) } }
    }
}

@Composable
fun SalesBanner() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(16.dp).height(100.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("SUMMER SALES", style = MaterialTheme.typography.headlineSmall, color = Color.White, fontWeight = FontWeight.Bold)
            Text("Up to 50% off", color = Color.White)
        }
    }
}

@Composable
fun CategoryItem(categoryName: String, imageRes: Int, onClick: () -> Unit) {
    val painter = rememberAsyncImagePainter(imageRes)
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp).clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().height(100.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = categoryName,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 23.dp),
                fontSize = 18.sp,
                lineHeight = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Image(
                painter = painter,
                contentDescription = categoryName,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShopScreenPreview() {
    val navController = rememberNavController()
    ShopScreen(navController = navController)
}
