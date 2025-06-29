package com.pab.nusabite.data.model

data class ProductsResponse(
    val code: Int,
    val message: String,
    val data: List<Product>
)

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String,
)
