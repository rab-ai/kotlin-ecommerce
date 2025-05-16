package com.example.e_commerce.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.e_commerce.R
import com.example.e_commerce.data.repository.ProductRepository
import com.example.e_commerce.ui.navigation.Routes
import com.example.e_commerce.data.model.Product

@Composable
fun HomeScreen(navController: NavController) {
    LazyColumn {
        item {
            ClickableImage(
                imageResId = R.drawable.image_4,
                contentDescription = "new_collection",
                modifier = Modifier
                    .fillMaxWidth(),
                navController = navController
            )
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    ClickableImage(
                        imageResId = R.drawable.rectangle_2,
                        contentDescription = "black",
                        modifier = Modifier.fillMaxWidth(),
                        navController = navController
                    )
                    ClickableImage(
                        imageResId = R.drawable.image_2__1_,
                        contentDescription = "black",
                        modifier = Modifier.fillMaxWidth(),
                        navController = navController
                    )
                }

                ClickableImage(
                    imageResId = R.drawable.image_2,
                    contentDescription = "men's_hoodies",
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    navController = navController
                )
            }
        }

        item {
            ClickableImage(
                imageResId = R.drawable.small_banner,
                contentDescription = "street clothes",
                modifier = Modifier
                    .height(196.dp)
                    .fillMaxWidth(),
                navController = navController
            )
        }

        item {
            SectionHeader(title = "Sale", navController, subTitle = "Super summer sale")
            HorizontalProductList(products = ProductRepository.getSaleProducts(), navController)
        }

        item {
            SectionHeader(title = "New", navController, subTitle = "You've never seen it before")
            HorizontalProductList(products = ProductRepository.getNewProducts(), navController)
        }
    }
}

@Composable
fun ClickableImage(
    imageResId: Int,
    contentDescription: String,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Image(
        painter = painterResource(imageResId),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier.clickable { navController.navigate(Routes.SHOP) }
    )
}

@Composable
fun SectionHeader(title: String, navController: NavController, subTitle: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 37.dp)
            .padding(horizontal = 18.dp)
            .padding(bottom = 22.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = title, fontSize = 34.sp, fontWeight = FontWeight.Bold)
            Text(text = subTitle, fontSize = 11.sp, fontWeight = FontWeight.Normal, color = MaterialTheme.colorScheme.outline)
        }
        Text(
            text = "View all",
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.clickable { navController.navigate(Routes.SHOP) }
        )
    }
}

@Composable
fun ProductCard(product: Product, navController: NavController) {
    Column(
        modifier = Modifier
            .padding(start = 16.dp)
            .wrapContentWidth()
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
                Column {
                    Box {
                        Image(
                            painter = painterResource(product.imageRes),
                            contentDescription = product.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.height(180.dp)
                        )
                        if (product.discount > 0) {
                            Text(
                                text = "-${product.discount}%",
                                color = Color.White,
                                fontSize = 11.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(horizontal = 4.dp, vertical = 2.dp)
                                    .padding(start = 5.dp, top = 7.dp)
                                    .width(40.dp)
                                    .height(24.dp)
                                    .background(Color(0xFFDB3022), shape = MaterialTheme.shapes.medium),
                            )

                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .offset(y = 18.dp)
                    .size(36.dp)
                    .background(MaterialTheme.colorScheme.onPrimary, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = MaterialTheme.colorScheme.outline,
                    modifier = Modifier
                        .width(13.08.dp)
                        .height(12.dp)
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = product.brand,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = product.name,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 2.dp)
            )
            Row {
                Text(
                    text = "${product.price}$",
                    textDecoration = TextDecoration.LineThrough,
                    color = MaterialTheme.colorScheme.outline,
                    fontSize = 14.sp
                )
                Text(
                    text = " ${product.newPrice}$",
                    color = Color.Red,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun HorizontalProductList(products: List<Product>, navController: NavController) {
    LazyRow {
        items(products) { product ->
            ProductCard(product, navController)
        }
    }
}

@Preview
@Composable
fun HomescreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}