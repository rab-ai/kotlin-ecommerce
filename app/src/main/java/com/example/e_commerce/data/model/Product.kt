package com.example.e_commerce.data.model

data class Product(
    val id: Int,
    val brand: String,
    val name: String,
    val price: Int,
    val newPrice: Int,
    val imageRes: Int,
    val discount: Int,
    val rating: Double,
    val category: String, // "Women", "Men", "Boys", "Girls"
    val subcategory: String,
    val colors: List<String>,
    val sizes: List<String>,
    val popularity: Int,
    val imageUrl: String
)