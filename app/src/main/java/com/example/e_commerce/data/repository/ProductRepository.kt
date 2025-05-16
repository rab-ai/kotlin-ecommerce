package com.example.e_commerce.data.repository

import com.example.e_commerce.data.source.ProductList.productList
import com.example.e_commerce.data.model.Product

object ProductRepository {

    fun getSaleProducts(): List<Product> {
        return productList.filter { it.discount > 10 }
    }

    fun getNewProducts(): List<Product> {
        return productList.filter { it.popularity > 80 }
    }

    fun getProductsByCategoryAndSubcategory(category: String, subcategory: String): List<Product> {
        return productList.filter { product ->
            product.category == category && product.subcategory == subcategory
        }
    }
}
