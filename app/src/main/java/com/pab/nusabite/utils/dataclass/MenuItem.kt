package com.pab.nusabite.utils.dataclass

import com.google.gson.annotations.SerializedName

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val image: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val category: String
)


data class ApiProperty(
    val code: Int,
    val message: String,
    val data: List<Product>
)

data class CategoryRequest(
    val category_id: Int
)