package com.pab.nusabite.utils.dataclass

data class CartItem(
    val id: Int,
    val name: String,
    val price: Double,
    val quantity: Int,
    val image: String,
    val description: String? = null,
    val category: String? = null
)
