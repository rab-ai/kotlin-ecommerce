package com.example.e_commerce.ui.screens.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.KeyboardArrowLeft
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.e_commerce.R
import com.example.e_commerce.data.model.Product
import com.example.e_commerce.data.repository.ProductRepository
import com.example.e_commerce.ui.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDetailScreen(parentCategory: String, categoryName: String,navController: NavController) {
    var showFilterDialog by remember { mutableStateOf(false) }
    var showSortDialog by remember { mutableStateOf(false) }
    val products = remember { ProductRepository.getNewProducts() }
    var filteredProducts by remember { mutableStateOf(products) }
    var selectedSortOption by remember { mutableStateOf(SortOption.POPULAR) }

    val items = listOf("T-Shirts", "Crop tops", "Sleeveless", "Blouses")
    val sheetState = rememberModalBottomSheetState()

    if (showFilterDialog) {
        ModalBottomSheet(
            onDismissRequest = { showFilterDialog = false },
            sheetState = sheetState,
            modifier = Modifier.fillMaxSize()
        ) {
            FilterDialog(
                onDismiss = { showFilterDialog = false },
                onApplyFilters = { filters ->
                    filteredProducts = applyFilters(products, filters)
                    showFilterDialog = false
                }
            )
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { /*Text("$parentCategory's $categoryName")*/ },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Sharp.KeyboardArrowLeft, contentDescription = "Back")
                    }
                },
                modifier = Modifier
                    .height(106.dp)
                    .background(MaterialTheme.colorScheme.onPrimary)
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(
                text = "$parentCategory's $categoryName",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 14.dp)
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items) { item ->
                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(30.dp)
                            .background(Color(0xFF222222), shape = MaterialTheme.shapes.medium),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = item,
                            color = Color.White,
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .padding(horizontal = 16.dp)
                    .background(MaterialTheme.colorScheme.onPrimary),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { showFilterDialog = true },
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.filter),
                            contentDescription = "Sort",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Text(
                        text = "Filters",
                        fontSize = 11.sp,
                        lineHeight = 11.sp
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { showSortDialog = true },
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.sort),
                            contentDescription = "Sort",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Text(
                        text = selectedSortOption.displayName,
                        fontSize = 11.sp,
                        lineHeight = 11.sp
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.view),
                    contentDescription = "Sort",
                    modifier = Modifier.size(24.dp)
                )
            }

            LazyColumn {
                items(filteredProducts) { product ->
                    ProductCard(product = product, navController = navController)
                }
            }
        }
    }

    if (showSortDialog) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .clickable { showSortDialog = false },
            contentAlignment = Alignment.TopEnd
        ) {
            SortDialog(
                selectedSortOption = selectedSortOption,
                onSortSelected = { sortOption ->
                    selectedSortOption = sortOption
                    filteredProducts = sortProducts(filteredProducts, sortOption)
                    showSortDialog = false
                },
                onDismiss = { showSortDialog = false }
            )
        }
    }
}

@Composable
fun ProductCard(product: Product, navController: NavController) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(Routes.SHOP) },
                shape = MaterialTheme.shapes.medium,
                elevation = CardDefaults.cardElevation(4.dp),
            ) {
                Row {
                    Box {
                        Image(
                            painter = painterResource(product.imageRes),
                            contentDescription = product.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(104.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                    ) {
                        Text(
                            text = product.name,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1
                        )
                        Text(
                            text = product.brand,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.outline
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "${product.newPrice}$",
                                fontSize = 14.sp,
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .offset(x = (-2).dp, y = (18).dp)
                    .size(36.dp)
                    .background(MaterialTheme.colorScheme.onPrimary, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}


data class Filters(
    val priceRange: ClosedFloatingPointRange<Float>,
    val colors: Set<String>,
    val sizes: Set<String>,
    val category: String,
    val brand: String
)

fun applyFilters(products: List<Product>, filters: Filters): List<Product> {
    return products.filter { product ->
        product.price in filters.priceRange.start.toInt()..filters.priceRange.endInclusive.toInt() &&
                (filters.colors.isEmpty() || product.colors.any { it in filters.colors }) &&
                (filters.sizes.isEmpty() || product.sizes.any { it in filters.sizes }) &&
                (filters.category == "All" || product.category == filters.category) &&
                (filters.brand.isEmpty() || product.brand == filters.brand)
    }
}

fun sortProducts(products: List<Product>, sortOption: SortOption): List<Product> {
    return when (sortOption) {
        SortOption.POPULAR -> products.sortedByDescending { it.popularity }
        SortOption.NEWEST -> products.sortedByDescending { it.id }
        SortOption.PRICE_LOW_TO_HIGH -> products.sortedBy { it.price }
        SortOption.PRICE_HIGH_TO_LOW -> products.sortedByDescending { it.price }
    }
}



enum class SortOption(val displayName: String) {
    POPULAR("Popular"),
    NEWEST("Newest"),
    PRICE_LOW_TO_HIGH("Price: Lowest to High"),
    PRICE_HIGH_TO_LOW("Price: Highest to Low")
}



@Composable
fun FilterDialog(onDismiss: () -> Unit, onApplyFilters: (Filters) -> Unit) {
    var priceRange by remember { mutableStateOf(0f..1000f) }
    var selectedColors by remember { mutableStateOf(emptySet<String>()) }
    var selectedSizes by remember { mutableStateOf(emptySet<String>()) }
    var selectedCategory by remember { mutableStateOf("All") }
    var selectedBrand by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onPrimary)
        ) {
            IconButton(
                onClick = onDismiss,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Sharp.KeyboardArrowLeft,
                    contentDescription = "Close"
                )
            }
            Text(
                text = "Filters",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Text(
            text = "Price Range: $${priceRange.start.toInt()} - $${priceRange.endInclusive.toInt()}",
            modifier = Modifier.padding(top = 16.dp)
        )
        RangeSlider(
            value = priceRange,
            onValueChange = { priceRange = it },
            valueRange = 0f..1000f,
            steps = 10,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = "Color",
            modifier = Modifier.padding(top = 16.dp)
        )
        Row(
            modifier = Modifier.padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("Red", "Blue", "Green", "Black", "White").forEach { color ->
                FilterChip(
                    selected = selectedColors.contains(color),
                    onClick = {
                        selectedColors = if (selectedColors.contains(color)) {
                            selectedColors - color
                        } else {
                            selectedColors + color
                        }
                    },
                    label = { Text(color) }
                )
            }
        }

        Text(
            text = "Size",
            modifier = Modifier.padding(top = 16.dp)
        )
        Row(
            modifier = Modifier.padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("S", "M", "L", "XL").forEach { size ->
                FilterChip(
                    selected = selectedSizes.contains(size),
                    onClick = {
                        selectedSizes = if (selectedSizes.contains(size)) {
                            selectedSizes - size
                        } else {
                            selectedSizes + size
                        }
                    },
                    label = { Text(size) }
                )
            }
        }

        Text(
            text = "Category",
            modifier = Modifier.padding(top = 16.dp)
        )
        Row(
            modifier = Modifier.padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("All", "Women", "Men", "Girls", "Boys").forEach { category ->
                FilterChip(
                    selected = selectedCategory == category,
                    onClick = { selectedCategory = category },
                    label = { Text(category) }
                )
            }
        }

        Text(
            text = "Brand",
            modifier = Modifier.padding(top = 16.dp)
        )
        TextField(
            value = selectedBrand,
            onValueChange = { selectedBrand = it },
            label = { Text("Enter brand") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        Button(
            onClick = {
                onApplyFilters(
                    Filters(
                        priceRange,
                        selectedColors,
                        selectedSizes,
                        selectedCategory,
                        selectedBrand
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            Text("Apply Filters")
        }
    }
}

@Composable
fun FilterChip(
    selected: Boolean,
    onClick: () -> Unit,
    label: @Composable () -> Unit
) {
    Surface(
        color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .clickable { onClick() }
            .border(
                width = 1.dp,
                color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                shape = MaterialTheme.shapes.small
            )
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            label()
        }
    }
}

@Composable
fun SortDialog(
    selectedSortOption: SortOption,
    onSortSelected: (SortOption) -> Unit,
    onDismiss: () -> Unit
) {
    var expanded by remember { mutableStateOf(true) }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss,
        modifier = Modifier.fillMaxWidth()
    ) {
        SortOption.entries.forEach { option ->
            DropdownMenuItem(
                onClick = {
                    onSortSelected(option)
                    expanded = false
                },
                text = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        RadioButton(
                            selected = selectedSortOption == option,
                            onClick = null
                        )
                        Text(
                            text = option.displayName,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            )
        }
    }
}
@Composable
fun ProductItem(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = product.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "Brand: ${product.brand}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Price: $${product.price}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Category: ${product.category}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Colors: ${product.colors.joinToString()}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Sizes: ${product.sizes.joinToString()}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryDetailScreenPreview() {
    val fakeNavController = rememberNavController()

    CategoryDetailScreen(
        parentCategory = "Men",
        categoryName = "Shoes",
        navController = fakeNavController
    )
}
