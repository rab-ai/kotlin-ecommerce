package com.example.e_commerce.ui.screens.category
import com.example.e_commerce.R
object CategoryData {
    val womenCategories = listOf(
        "New" to R.drawable.img_new,
        "Clothes" to R.drawable.clothes,
        "Dresses" to R.drawable.img_new,
        "Tops" to R.drawable.img_new,
        "Bottoms" to R.drawable.img_new,
        "Shoes" to R.drawable.shoes,
        "Heels" to R.drawable.img_new,
        "Sneakers" to R.drawable.img_new,
        "Boots" to R.drawable.img_new,
        "Sandals" to R.drawable.img_new,
        "Accessories" to R.drawable.accessories,
        "Bags" to R.drawable.img_new,
        "Jewelry" to R.drawable.img_new,
        "Sunglasses" to R.drawable.img_new,
        "Watches" to R.drawable.img_new
    )

    val menCategories = listOf(
        "New" to R.drawable.new_male,
        "Tops" to R.drawable.img_new,
        "T-Shirts" to R.drawable.img_new,
        "Shirts" to R.drawable.img_new,
        "Jackets" to R.drawable.img_new,
        "Bottoms" to R.drawable.men_bottom,
        "Jeans" to R.drawable.img_new,
        "Shorts" to R.drawable.img_new,
        "Sweatpants" to R.drawable.img_new,
        "Shoes" to R.drawable.men_shoes,
        "Sneakers" to R.drawable.img_new,
        "Loafers" to R.drawable.img_new,
        "Boots" to R.drawable.img_new,
        "Accessories" to R.drawable.men_accessories,
        "Belts" to R.drawable.img_new,
        "Watches" to R.drawable.img_new,
        "Sunglasses" to R.drawable.img_new
    )

    val kidsCategories = listOf(
        "New" to R.drawable.kid_new,
        "Tops" to R.drawable.kid_top,
        "T-Shirts" to R.drawable.img_new,
        "Sweatshirts" to R.drawable.img_new,
        "Bottoms" to R.drawable.kid_bottom,
        "Jeans" to R.drawable.img_new,
        "Shorts" to R.drawable.img_new,
        "Leggings" to R.drawable.img_new,
        "Shoes" to R.drawable.kid_shoes,
        "Sneakers" to R.drawable.img_new,
        "Boots" to R.drawable.img_new,
        "Sandals" to R.drawable.img_new,
        "Accessories" to R.drawable.kid_accessories,
        "Hats" to R.drawable.img_new,
        "Bags" to R.drawable.img_new,
        "Socks" to R.drawable.img_new
    )

    fun getCategoriesForTab(selectedTab: String) = when (selectedTab) {
        "Women" -> womenCategories
        "Men" -> menCategories
        "Kids" -> kidsCategories
        else -> emptyList()
    }
}