package com.example.e_commerce.data.source

import com.example.e_commerce.R
import com.example.e_commerce.data.model.Product

object ProductList {
    val productList = listOf(
        Product(
            id = 1,
            brand = "Dorothy Perkins",
            name = "Evening Dress",
            price = 15,
            newPrice = 12,
            imageRes = R.drawable.evening_dress,
            discount = 20,
            rating = 4.5,
            category = "Women",
            subcategory = "Dresses",
            colors = listOf("Red", "Black"),
            sizes = listOf("S", "M", "L"),
            popularity = 90,
            imageUrl = ""
        ),
        Product(
            id = 2,
            brand = "Stilly",
            name = "Sport Dress",
            price = 22,
            newPrice = 19,
            imageRes = R.drawable.evening_dress,
            discount = 15,
            rating = 4.0,
            category = "Women",
            subcategory = "Dresses",
            colors = listOf("Blue", "White"),
            sizes = listOf("M", "L"),
            popularity = 85,
            imageUrl = ""
        ),
        Product(
            id = 3,
            brand = "Dorothy Perkins",
            name = "Evening Dress",
            price = 15,
            newPrice = 12,
            imageRes = R.drawable.evening_dress,
            discount = 20,
            rating = 4.5,
            category = "Women",
            subcategory = "Dresses",
            colors = listOf("Black", "White"),
            sizes = listOf("S", "M", "L"),
            popularity = 75,
            imageUrl = ""
        ),
        Product(
            id = 4,
            brand = "New Brand",
            name = "Cool Jacket",
            price = 30,
            newPrice = 25,
            imageRes = R.drawable.evening_dress,
            discount = 10,
            rating = 4.7,
            category = "Men",
            subcategory = "Jackets",
            colors = listOf("Blue", "Gray"),
            sizes = listOf("M", "L", "XL"),
            popularity = 95,
            imageUrl = ""
        ),
        Product(
            id = 5,
            brand = "Trendy",
            name = "Casual Shirt",
            price = 20,
            newPrice = 18,
            imageRes = R.drawable.evening_dress,
            discount = 5,
            rating = 4.3,
            category = "Men",
            subcategory = "Tops",
            colors = listOf("White", "Black"),
            sizes = listOf("S", "M", "L"),
            popularity = 80,
            imageUrl = ""
        )
    )
}